/*
 * Copyright (C) 2022  The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.husseinrasti.core.network

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.paging.PagingSource
import com.google.gson.Gson
import com.husseinrasti.core.R
import com.husseinrasti.core.entity.ErrorResponse
import com.husseinrasti.core.exceptions.Failure
import com.husseinrasti.core.exceptions.toFailure
import com.husseinrasti.core.mapper.ResponseObject
import com.husseinrasti.core.utils.NETWORK_PAGE_SIZE
import com.husseinrasti.core.utils.STARTING_PAGE_INDEX
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response


/**
 * Created by Hussein Rasti on 2/22/22.
 */


fun Response<*>.errorResponse(errorMessage: String): Failure {
    return when (code()) {
        in 500..599 -> Failure.InternalServerError(errorBody()?.string() ?: errorMessage, code())
        401 -> Failure.UnAuthorized
        in 400..499 -> {
            val errorResponseObject = try {
                Gson().fromJson(errorBody()!!.string(), ErrorResponse::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
                ErrorResponse(errorBody()?.string() ?: errorMessage)
            }
            Failure.BadRequest(
                message = errorResponseObject.error,
                code = code()
            )
        }
        else -> Failure.Validate(errorMessage, code())
    }
}


suspend inline fun call(
    dispatcher: CoroutineDispatcher,
    resources: Resources,
    @StringRes errorMessage: Int = R.string.msg_error,
    crossinline fetch: suspend () -> Response<Unit>
): Result<String> {
    return withContext(dispatcher) {
        try {
            val response = fetch()
            if (response.isSuccessful) Result.success(response.body().toString())
            else Result.failure(response.errorResponse(resources.getString(errorMessage)))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e.toFailure(resources.getString(errorMessage)))
        }
    }
}


suspend inline fun <RequestType : ResponseObject<Entity>, Entity : Any> fetch(
    dispatcher: CoroutineDispatcher,
    resources: Resources,
    @StringRes errorMessage: Int = R.string.msg_error,
    crossinline fetch: suspend () -> Response<RequestType>,
): Result<Entity> {
    return withContext(dispatcher) {
        try {
            val response = fetch()
            if (response.isSuccessful) Result.success(response.body()!!.toDomain())
            else Result.failure(response.errorResponse(resources.getString(errorMessage)))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e.toFailure(resources.getString(errorMessage)))
        }
    }
}


suspend inline fun <RequestType : ResponseObject<Entity>, Entity : Any> fetch(
    dispatcher: CoroutineDispatcher,
    resources: Resources,
    @StringRes emptyMessage: Int = R.string.msg_empty,
    @StringRes errorMessage: Int = R.string.msg_error,
    crossinline fetch: suspend () -> Response<List<RequestType>>
): Result<List<Entity>> {
    return withContext(dispatcher) {
        try {
            val response = fetch()
            if (response.isSuccessful) Result.success(response.body()!!.map { it.toDomain() })
            else Result.failure(response.errorResponse(resources.getString(errorMessage)))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e.toFailure(resources.getString(emptyMessage)))
        }
    }
}


suspend inline fun <RequestType : ResponseObject<Entity>, Entity : Any> fetchPage(
    resources: Resources,
    position: Int,
    loadSize: Int,
    @StringRes errorMessage: Int = R.string.msg_error,
    crossinline fetch: suspend () -> Response<List<RequestType>>
): PagingSource.LoadResult<Int, Entity> {
    return try {
        val response = fetch.invoke()
        if (response.isSuccessful) PagingSource.LoadResult.Page(
            data = response.body()!!.map { item -> item.toDomain() },
            prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
            nextKey = if (response.body().isNullOrEmpty()) null else (position + (loadSize / NETWORK_PAGE_SIZE)),
            itemsBefore = PagingSource.LoadResult.Page.COUNT_UNDEFINED,
            itemsAfter = PagingSource.LoadResult.Page.COUNT_UNDEFINED
        )
        else PagingSource.LoadResult.Error(response.errorResponse(resources.getString(errorMessage)))
    } catch (e: Exception) {
        e.printStackTrace()
        PagingSource.LoadResult.Error(e.toFailure())
    }
}