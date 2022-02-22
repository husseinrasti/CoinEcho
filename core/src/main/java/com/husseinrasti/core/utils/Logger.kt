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

package com.husseinrasti.core.utils

import android.util.Log
import com.husseinrasti.core.BuildConfig


/**
 * Created by Hussein Rasti on 2/22/22.
 */
object Logger {

    fun i(message: String?, tag: String = "CoinEcho") {
        if (BuildConfig.DEBUG) Log.i(tag, "$message")
    }

    fun d(message: String?, tag: String = "CoinEcho") {
        if (BuildConfig.DEBUG) Log.d(tag, "$message")
    }

    fun e(message: String?, tag: String = "CoinEcho") {
        if (BuildConfig.DEBUG) Log.e(tag, "$message")
    }

    fun v(message: String?, tag: String = "CoinEcho") {
        if (BuildConfig.DEBUG) Log.wtf(tag, "$message")
    }

    fun w(message: String?, tag: String = "CoinEcho") {
        if (BuildConfig.DEBUG) Log.w(tag, "$message")
    }

}