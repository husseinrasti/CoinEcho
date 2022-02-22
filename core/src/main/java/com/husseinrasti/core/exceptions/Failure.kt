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

package com.husseinrasti.core.exceptions

import com.husseinrasti.core.utils.*
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException


/**
 * Created by Hussein Rasti on 2/22/22.
 */
sealed class Failure : Throwable() {
    data class Validate(override val message: String?, val code: Int? = FAILED_API_VALIDATION_CODE) : Failure()
    data class BadRequest(override val message: String?, val code: Int? = HTTP_ERROR_BAD_REQUEST) : Failure()
    data class InternalServerError(override val message: String?, val code: Int? = HTTP_ERROR_INTERNAL_SERVER_ERROR) : Failure()
    data class Unknown(override val message: String?, val code: Int? = FAILED_CODE) : Failure()
    object Connectivity : Failure()
    object UnAuthorized : Failure()
}


fun Throwable.toFailure(msg: String? = null): Failure = if (this is Failure) {
    this
} else {
    when (this) {
        is HttpException -> this.convertHttpException(msg)
        is Failure.Connectivity, is SocketTimeoutException, is IOException -> Failure.Connectivity
        is Failure.BadRequest -> Failure.BadRequest(msg ?: this.message, this.code)
        is Failure.InternalServerError -> Failure.InternalServerError(msg ?: this.message, this.code)
        is Failure.UnAuthorized -> Failure.UnAuthorized
        else -> Failure.Unknown(msg ?: this.message)
    }
}

fun HttpException.convertHttpException(msg: String? = null): Failure = when (this.code()) {
    401 -> Failure.UnAuthorized
    in 400..499 -> Failure.BadRequest(msg ?: this.message(), this.code())
    in 500..599 -> Failure.InternalServerError(msg ?: this.message(), this.code())
    else -> Failure.Connectivity
}
