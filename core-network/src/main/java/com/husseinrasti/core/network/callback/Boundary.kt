package com.husseinrasti.core.network.callback

import android.content.res.Resources
import androidx.annotation.StringRes
import com.husseinrasti.core.network.R
import com.husseinrasti.core.model.toFailure
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

inline fun <reified ResultType, reified RequestType> networkBoundResource(
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