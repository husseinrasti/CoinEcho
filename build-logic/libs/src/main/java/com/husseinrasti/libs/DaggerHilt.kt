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

package com.husseinrasti.libs


/**
 * Created by Hussein Rasti on 2/22/22.
 */
object DaggerHilt {
    // Dagger Core
    const val daggerCore = "com.google.dagger:dagger:${BuildDependenciesVersions.daggerHilt}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${BuildDependenciesVersions.daggerHilt}"

    // Dagger Android
    const val daggerAndroid = "com.google.dagger:dagger-android:${BuildDependenciesVersions.daggerHilt}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${BuildDependenciesVersions.daggerHilt}"
    const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${BuildDependenciesVersions.daggerHilt}"

    // Dagger - Hilt
    const val daggerHilt = "com.google.dagger:hilt-android:${BuildDependenciesVersions.daggerHilt}"
    const val daggerHiltCompiler = "com.google.dagger:hilt-compiler:${BuildDependenciesVersions.daggerHilt}"
    const val daggerHiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${BuildDependenciesVersions.daggerHilt}"
}