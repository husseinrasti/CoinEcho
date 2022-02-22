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

package com.husseinrasti.coinecho.dependencies

import com.husseinrasti.coinecho.BuildDependenciesVersions


/**
 * Created by Hussein Rasti on 2/22/22.
 */
object AndroidTestDependencies {
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:${BuildDependenciesVersions.kotlin}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${BuildDependenciesVersions.coroutinesVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${BuildDependenciesVersions.espressoCore}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${BuildDependenciesVersions.espressoCore}"
    const val idlingResource = "androidx.test.espresso:espresso-idling-resource:${BuildDependenciesVersions.espressoIdlingResource}"
    const val testRunner = "androidx.test:runner:${BuildDependenciesVersions.testRunner}"
    const val testRules = "androidx.test:rules:${BuildDependenciesVersions.testRunner}"
    const val textCoreKtx = "androidx.test:core-ktx:${BuildDependenciesVersions.testCore}"
    const val mockkAndroid = "io.mockk:mockk-android:${BuildDependenciesVersions.mockkVersion}"
    const val fragmentTesting = "androidx.fragment:fragment-testing:${BuildDependenciesVersions.fragmentVersion}"
    const val androidxTestExt = "androidx.test.ext:junit-ktx:${BuildDependenciesVersions.androidxTestExt}"
    const val navigationTesting = "androidx.navigation:navigation-testing:${BuildDependenciesVersions.navigation}"

    const val instrumentationRunner = "com.codingwithmitch.cleannotes.framework.MockTestRunner"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}