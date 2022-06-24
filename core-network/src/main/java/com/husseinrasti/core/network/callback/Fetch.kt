package com.husseinrasti.core.network.callback

import android.content.res.Resources
import androidx.annotation.StringRes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import com.husseinrasti.core.network.R
import com.husseinrasti.core.model.toFailure
import com.husseinrasti.core.model.ResponseObject
import retrofit2.Response


suspend inline fun <reified RequestType : ResponseObject<Entity>, reified Entity : Any> fetch(
    dispatcher: CoroutineDispatcher,
    resources: Resources,
    @StringRes errorMessage: Int = R.string.msg_error,
    noinline saveFetchResult: (suspend (Entity) -> Unit)? = null,
    crossinline fetch: suspend () -> Response<RequestType>,
): Result<Entity> {
    return withContext(dispatcher) {
        try {
            val response = fetch()
            if (response.isSuccessful) {
                val result = response.body()!!.toDomain()
                saveFetchResult?.invoke(result)
                Result.success(result)
            } else Result.failure(response.errorResponse(resources.getString(errorMessage)))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e.toFailure(resources.getString(errorMessage)))
        }
    }
}


suspend inline fun <reified RequestType : ResponseObject<Entity>, reified Entity : Any> fetch(
    dispatcher: CoroutineDispatcher,
    resources: Resources,
    @StringRes emptyMessage: Int = R.string.msg_empty,
    @StringRes errorMessage: Int = R.string.msg_error,
    noinline saveFetchResult: (suspend (List<Entity>) -> Unit)? = null,
    crossinline fetchList: suspend () -> Response<List<RequestType>>
): Result<List<Entity>> {
    return withContext(dispatcher) {
        try {
            val response = fetchList()
            if (response.isSuccessful) {
                val result = response.body()!!.map { it.toDomain() }
                saveFetchResult?.invoke(result)
                Result.success(result)
            } else Result.failure(response.errorResponse(resources.getString(errorMessage)))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e.toFailure(resources.getString(emptyMessage)))
        }
    }
}