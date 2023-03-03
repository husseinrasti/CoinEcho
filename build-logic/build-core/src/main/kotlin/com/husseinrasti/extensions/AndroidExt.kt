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

package com.husseinrasti.extensions


import com.android.build.gradle.LibraryExtension
import com.husseinrasti.build_core.*
import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.extra


/**
 * Created by Hussein Rasti on 2/22/22.
 */

internal fun Project.android(configure: Action<LibraryExtension>): Unit =
    (this as ExtensionAware).extensions.configure("android", configure)

fun Project.androidLibrary() {
    android {
        compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION
        defaultConfig.multiDexEnabled = true
        defaultConfig.minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        defaultConfig.targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION

        buildTypes.apply {
            getByName(BuildType.RELEASE) {
                isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
                isTestCoverageEnabled = BuildTypeRelease.isTestCoverageEnabled
            }
            getByName(BuildType.DEBUG) {
                isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
                isTestCoverageEnabled = BuildTypeDebug.isTestCoverageEnabled
                extra["enableCrashlytics"] = false
                extra["alwaysUpdateBuildId"] = false
            }
        }

        buildFeatures.viewBinding = true

        flavorDimensions.add(BuildProductDimensions.ENVIRONMENT)
        productFlavors.apply {
            ProductFlavorDevelop.libraryCreate(this)
            ProductFlavorProduction.libraryCreate(this)
        }

        compileOptions.sourceCompatibility = JavaVersion.VERSION_1_8
        compileOptions.targetCompatibility = JavaVersion.VERSION_1_8

        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
}