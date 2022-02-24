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

import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import com.husseinrasti.coinecho.*
import com.husseinrasti.coinecho.BuildType
import com.husseinrasti.coinecho.dependencies.*

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
//    id("com.google.gms.google-services")
//    id("com.google.firebase.crashlytics")
    id("coinecho-config")
}

android {
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION
    buildToolsVersion = BuildAndroidConfig.BUILD_TOOLS_VERSION
    defaultConfig.apply {
        multiDexEnabled = true
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION
        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME
        vectorDrawables.useSupportLibrary = BuildAndroidConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES
        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildFeatures.viewBinding = true

    buildTypes.apply {
        getByName(BuildType.RELEASE) {
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            isShrinkResources = BuildTypeRelease.isShrinkResources
            isTestCoverageEnabled = BuildTypeRelease.isTestCoverageEnabled
        }

        getByName(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isShrinkResources = BuildTypeDebug.isShrinkResources
            isTestCoverageEnabled = BuildTypeDebug.isTestCoverageEnabled
            extra["enableCrashlytics"] = false
            extra["alwaysUpdateBuildId"] = false
        }
    }

    flavorDimensions.add(BuildProductDimensions.ENVIRONMENT)
    productFlavors.apply {
        ProductFlavorDevelop.appCreate(this)
        ProductFlavorProduction.appCreate(this)
    }

    compileOptions.apply {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    lint.apply {
        lintConfig = rootProject.file(".lint/config.xml")
        isCheckAllWarnings = false
        isWarningsAsErrors = false
        isCheckReleaseBuilds = false
    }

    applicationVariants.all {
        val variant = this
        variant.outputs
            .map { it as BaseVariantOutputImpl }
            .forEach { output ->
                output.outputFileName = "CoinEcho_${variant.name}_v.${variant.versionName}.apk"
            }
    }

}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(Dependencies.kotlin)
    implementation(Dependencies.coreKtx)
    implementation(SupportDependencies.appcompat)
    implementation(Dependencies.multidex)
    implementation(Dependencies.securityCrypto)
    implementation(SupportDependencies.legacySupport)
    implementation(SupportDependencies.materialDesign)
    implementation(SupportDependencies.constraintLayout)
    implementation(SupportDependencies.cardview)
    implementation(SupportDependencies.recyclerview)
    implementation(Dependencies.paging)
    implementation(Dependencies.preferenceKtx)
    implementation(Dependencies.activityKtx)
    implementation(Dependencies.fragmentKtx)
    implementation(Dependencies.lifecycleCommon)
    implementation(Dependencies.lifecycleCoroutines)
    implementation(Dependencies.lifecycleRuntime)
    implementation(Dependencies.lifecycleViewModel)
    implementation(ThirdParty.retrofit)
    implementation(ThirdParty.retrofitGson)
    implementation(ThirdParty.retrofitRxJavaAdapter)
    implementation(ThirdParty.googleGson)
    implementation(ThirdParty.okHttp)
    implementation(ThirdParty.okHttpInterceptor)
    implementation(Dependencies.navigationFragment)
    implementation(Dependencies.navigationUi)
    implementation(ThirdParty.glide)
    implementation(ThirdParty.glideCompiler)
    implementation(Dependencies.roomKtx)
    implementation(Dependencies.roomRuntime)
    kapt(AnnotationProcessing.roomCompiler)
    implementation(ThirdParty.shimmerEffect)

    implementation(DaggerHilt.daggerHilt)
    kapt(DaggerHilt.daggerHiltCompiler)

    implementation(project(BuildModules.CORE))
    implementation(project(BuildModules.DOMAIN))
    implementation(project(BuildModules.DATA))
    implementation(project(BuildModules.Features.MARKET))
}
