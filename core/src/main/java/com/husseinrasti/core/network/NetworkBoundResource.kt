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
import com.husseinrasti.core.R
import com.husseinrasti.core.exceptions.toFailure
import kotlinx.coroutines.flow.*
import retrofit2.Response


/**
 * Created by Hussein Rasti on 2/22/22.
 *
 * A inline function that can provide a resource backed by both the sqlite database and the network.
 * @param <ResultType>
 * @param <RequestType>
 *
 */

inline fun <ResultType, RequestType> networkBoundResource(
    resources: Resources,
    @StringRes errorMessage: Int = R.string.msg_error,
    crossinline query: () -> Flow<ResultType?>,
    crossinline fetch: suspend () -> Response<RequestType>,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType?) -> Boolean = { true }
) = flow {
    val data = query().first()
    val flow = if (shouldFetch(data)) {
        try {
            val response = fetch()
            if (response.isSuccessful) {
                saveFetchResult(response.body()!!)
                query().map { Result.success(it) }
            } else {
                query().map {
                    Result.failure<ResultType>(
                        response.errorResponse(resources.getString(errorMessage))
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            query().map { Result.failure(e.toFailure()) }
        }
    } else {
        query().map { Result.success(it) }
    }
    emitAll(flow)
}