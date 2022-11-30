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
object ThirdParty {
    const val glide = "com.github.bumptech.glide:glide:${BuildDependenciesVersions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${BuildDependenciesVersions.glide}"

    const val shimmerEffect = "com.facebook.shimmer:shimmer:0.5.0"

    const val retrofit = "com.squareup.retrofit2:retrofit:${BuildDependenciesVersions.retrofit2Version}"
    const val retrofitRxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava3:${BuildDependenciesVersions.retrofit2RxJava}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${BuildDependenciesVersions.retrofit2Version}"
    const val googleGson = "com.google.code.gson:gson:${BuildDependenciesVersions.gson}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${BuildDependenciesVersions.okHttp}"
    const val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${BuildDependenciesVersions.okHttp}"
}