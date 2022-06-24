package com.husseinrasti.core.network.callback

import com.google.gson.Gson
import com.husseinrasti.core.model.Failure
import com.husseinrasti.core.model.ErrorResponse
import retrofit2.Response


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