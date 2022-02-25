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

package com.husseinrasti.coinecho


/**
 * Created by Hussein Rasti on 2/22/22.
 */
interface BuildType {

    companion object {
        const val DEBUG = "debug"
        const val RELEASE = "release"
    }

    val isMinifyEnabled: Boolean
    val isTestCoverageEnabled: Boolean
    val isShrinkResources: Boolean
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled = false
    override val isTestCoverageEnabled = true
    override val isShrinkResources: Boolean = false
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled = true
    override val isTestCoverageEnabled = false
    override val isShrinkResources: Boolean = true
}