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
object BuildDependencies {
    const val buildTools = "com.android.tools.build:gradle:${BuildDependenciesVersions.gradle}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${BuildDependenciesVersions.kotlin}"
    const val junit5 = "de.mannodermaus.gradle.plugins:android-junit5:${BuildDependenciesVersions.junit5Version}"
    const val fabricIo = "io.fabric.tools:gradle:${BuildDependenciesVersions.fabricIo}"
    const val daggerHilt = "com.google.dagger:hilt-android-gradle-plugin:${BuildDependenciesVersions.daggerHilt}"
    const val navigation = "androidx.navigation:navigation-safe-args-gradle-plugin:${BuildDependenciesVersions.navigation}"
    const val googleServices = "com.google.gms:google-services:${BuildDependenciesVersions.playServices}"
    const val crashlyticsGradle = "com.google.firebase:firebase-crashlytics-gradle:${BuildDependenciesVersions.crashlyticsGradle}"
}