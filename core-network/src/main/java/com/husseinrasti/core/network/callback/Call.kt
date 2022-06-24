package com.husseinrasti.core.network.callback

import android.content.res.Resources
import androidx.annotation.StringRes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import com.husseinrasti.core.network.R
import com.husseinrasti.core.model.toFailure
import retrofit2.Response

suspend inline fun call(
    dispatcher: CoroutineDispatcher,
    resources: Resources,
    @StringRes errorMessage: Int = R.string.msg_error,
    noinline onSuccess: (suspend () -> Unit)? = null,
    crossinline fetch: suspend () -> Response<Unit>
): Result<String> {
    return withContext(dispatcher) {
        try {
            val response = fetch()
            if (response.isSuccessful) {
                onSuccess?.invoke()
                Result.success(response.body().toString())
            } else Result.failure(response.errorResponse(resources.getString(errorMessage)))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e.toFailure(resources.getString(errorMessage)))
        }
    }
}
