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

import com.husseinrasti.libs.*
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.support.delegates.DependencyHandlerDelegate

/**
 * Created by Hussein Rasti on 2/22/22.
 */

fun DependencyHandler.addJetpackDependencies() {
    implementation(Dependencies.kotlin)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.multidex)
    implementation(Dependencies.securityCrypto)
    implementation(Dependencies.paging)
    implementation(Dependencies.preferenceKtx)
    implementation(Dependencies.activityKtx)
    implementation(Dependencies.fragmentKtx)
    implementation(Dependencies.lifecycleCommon)
    implementation(Dependencies.lifecycleCoroutines)
    implementation(Dependencies.lifecycleRuntime)
    implementation(Dependencies.lifecycleViewModel)
    implementation(Dependencies.navigationFragment)
    implementation(Dependencies.navigationUi)
    implementation(Dependencies.roomKtx)
    implementation(Dependencies.roomRuntime)
    kapt(AnnotationProcessing.roomCompiler)
    implementation(DaggerHilt.daggerHilt)
    kapt(DaggerHilt.daggerHiltCompiler)
}

fun DependencyHandlerDelegate.addGoogleDependencies() {
    implementation(platform(Dependencies.firebaseBom))
    implementation(Dependencies.firebaseCrashlytics)
    implementation(Dependencies.firebaseConfig)
    implementation(Dependencies.firebaseAnalytics)
    implementation(Dependencies.firebaseCM)
    implementation(Dependencies.playCore)
    implementation(Dependencies.googleLocation)
    implementation(Dependencies.googlePlace)
    implementation(Dependencies.maps)
    implementation(Dependencies.mapsKtx)
    implementation(Dependencies.mapsUtilsKtx)
}

fun DependencyHandler.addThirdPartyDependencies() {
    implementation(ThirdParty.glide)
    implementation(ThirdParty.glideCompiler)
    implementation(ThirdParty.shimmerEffect)
    implementation(ThirdParty.retrofit)
    implementation(ThirdParty.retrofitGson)
    implementation(ThirdParty.googleGson)
    implementation(ThirdParty.okHttp)
    implementation(ThirdParty.okHttpInterceptor)
}

fun DependencyHandler.addSupportDependencies() {
    implementation(SupportDependencies.appcompat)
    implementation(SupportDependencies.legacySupport)
    implementation(SupportDependencies.materialDesign)
    implementation(SupportDependencies.constraintLayout)
    implementation(SupportDependencies.cardview)
    implementation(SupportDependencies.recyclerview)
    implementation(SupportDependencies.swipeRefreshLayout)
    implementation(SupportDependencies.viewpager2)
}

fun DependencyHandler.addTestsDependencies() {
    testImplementation(TestDependencies.espressoTest)
    testImplementation(TestDependencies.junit4)
    testImplementation(TestDependencies.junitTestAndroidX)
    testImplementation(TestDependencies.jupiterApi)
    testImplementation(TestDependencies.jupiterEngine)
    testImplementation(TestDependencies.jupiterParams)
    testImplementation(TestDependencies.mockk)
    testImplementation(TestDependencies.mockito)
    testImplementation(TestDependencies.mockitoInline)
    testImplementation(TestDependencies.mockitoKotlin)

    androidTestImplementation(AndroidTestDependencies.kotlinTest)
    androidTestImplementation(AndroidTestDependencies.coroutinesTest)
    androidTestImplementation(AndroidTestDependencies.espressoCore)
    androidTestImplementation(AndroidTestDependencies.espressoContrib)
    androidTestImplementation(AndroidTestDependencies.idlingResource)
    androidTestImplementation(AndroidTestDependencies.testRunner)
    androidTestImplementation(AndroidTestDependencies.testRules)
    androidTestImplementation(AndroidTestDependencies.textCoreKtx)
    androidTestImplementation(AndroidTestDependencies.mockkAndroid)
    androidTestImplementation(AndroidTestDependencies.fragmentTesting)
    androidTestImplementation(AndroidTestDependencies.androidxTestExt)
    androidTestImplementation(AndroidTestDependencies.navigationTesting)
    androidTestImplementation(AndroidTestDependencies.instrumentationRunner)
    androidTestImplementation(AndroidTestDependencies.testInstrumentationRunner)
}