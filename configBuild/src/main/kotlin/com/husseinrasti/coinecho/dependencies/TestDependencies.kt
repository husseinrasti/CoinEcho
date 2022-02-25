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
object TestDependencies {
    const val junitTestAndroidX = "androidx.test.ext:junit:${BuildDependenciesVersions.junitTestAndroidX}"
    const val espressoTest = "androidx.test.espresso:espresso-core:${BuildDependenciesVersions.espressoTest}"
    const val jupiterApi = "org.junit.jupiter:junit-jupiter-api:${BuildDependenciesVersions.junitJupiterVersion}"
    const val jupiterParams = "org.junit.jupiter:junit-jupiter-params:${BuildDependenciesVersions.junitJupiterVersion}"
    const val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${BuildDependenciesVersions.junitJupiterVersion}"
    const val mockk = "io.mockk:mockk:${BuildDependenciesVersions.mockkVersion}"
    const val junit4 = "junit:junit:${BuildDependenciesVersions.junit4Version}"

    const val mockito = "org.mockito:mockito-core:${BuildDependenciesVersions.mockitoCoreVersion}"
    const val mockitoInline = "org.mockito:mockito-inline:${BuildDependenciesVersions.mockitoInlineVersion}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${BuildDependenciesVersions.mockitoKotlinVersion}"
}