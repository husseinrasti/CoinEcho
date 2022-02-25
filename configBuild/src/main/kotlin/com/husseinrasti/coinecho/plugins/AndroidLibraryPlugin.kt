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

package com.husseinrasti.coinecho.plugins


import com.husseinrasti.coinecho.dependencies.*
import com.husseinrasti.coinecho.extensions.*
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


/**
 * Created by Hussein Rasti on 2/22/22.
 */
class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply("com.android.library")
        target.plugins.apply("kotlin-android")
        target.plugins.apply("kotlin-parcelize")
        target.plugins.apply("kotlin-kapt")
        target.plugins.apply("androidx.navigation.safeargs.kotlin")
        target.plugins.apply("dagger.hilt.android.plugin")
        target.androidLibrary()
        target.kapt { correctErrorTypes = true }
        target.dependencies {
            addJetpackDependencies()
            addSupportDependencies()
            addThirdPartyDependencies()
        }
    }
}