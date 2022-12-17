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
object Dependencies {
    const val guava = "com.google.guava:guava:${BuildDependenciesVersions.guava}"

    const val multidex = "androidx.multidex:multidex:${BuildDependenciesVersions.multidex}"

    const val securityCrypto = "androidx.security:security-crypto:${BuildDependenciesVersions.securityCrypto}"

    const val coreKtx = "androidx.core:core-ktx:${BuildDependenciesVersions.ktx}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${BuildDependenciesVersions.kotlin}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${BuildDependenciesVersions.kotlin}"
    const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${BuildDependenciesVersions.coroutinesVersion}"
    const val kotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildDependenciesVersions.coroutinesVersion}"
    const val kotlinCoroutinesPlayServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${BuildDependenciesVersions.coroutinesPlayServices}"

    const val preferenceKtx = "androidx.preference:preference-ktx:${BuildDependenciesVersions.preferenceKtx}"

    const val rxKotlin = "io.reactivex.rxjava3:rxkotlin:${BuildDependenciesVersions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${BuildDependenciesVersions.rxJava}"
    const val rxJava = "io.reactivex.rxjava3:rxjava:${BuildDependenciesVersions.rxJava}"

    const val rxBinding = "com.jakewharton.rxbinding4:rxbinding:${BuildDependenciesVersions.rxBinding}"
    const val rxBindingRecyclerView = "com.jakewharton.rxbinding2:rxbinding-recyclerview-v7:${BuildDependenciesVersions.rxBinding}"
    const val rxBindingSupportV4 = "com.jakewharton.rxbinding2:rxbinding-support-v4:${BuildDependenciesVersions.rxBinding}"
    const val rxBindingAppcompatV7 = "com.jakewharton.rxbinding2:rxbinding-appcompat-v7:${BuildDependenciesVersions.rxBinding}"
    const val rxBindingDesign = "com.jakewharton.rxbinding2:rxbinding-design:${BuildDependenciesVersions.rxBinding}"
    const val rxBindingLeanBack = "com.jakewharton.rxbinding2:rxbinding-leanback-v17:${BuildDependenciesVersions.rxBinding}"

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${BuildDependenciesVersions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${BuildDependenciesVersions.navigation}"
    const val navigationDynamic = "androidx.navigation:navigation-dynamic-features-fragment:${BuildDependenciesVersions.navigation}"

    const val roomRuntime = "androidx.room:room-runtime:${BuildDependenciesVersions.room}"
    const val roomKtx = "androidx.room:room-ktx:${BuildDependenciesVersions.room}"
    const val roomRxJava = "androidx.room:room-rxjava3:${BuildDependenciesVersions.room}"

    const val paging = "androidx.paging:paging-runtime-ktx:${BuildDependenciesVersions.paging}"
    const val rxPaging = "androidx.paging:paging-rxjava3:${BuildDependenciesVersions.paging}"

    const val firebaseBom = "com.google.firebase:firebase-bom:${BuildDependenciesVersions.firebase}"
    const val firebaseConfig = "com.google.firebase:firebase-config-ktx"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"

    const val playCore = "com.google.android.play:core:${BuildDependenciesVersions.playCore}"
    const val crashlytics = "com.crashlytics.sdk.android:crashlytics:${BuildDependenciesVersions.crashlytics}"
    const val firebaseFirestore = "com.google.firebase:firebase-firestore-ktx:${BuildDependenciesVersions.firebaseFirestore}"
    const val firebaseAuth = "com.google.firebase:firebase-auth:${BuildDependenciesVersions.firebaseAuth}"
    const val googlePlayAuth = "com.google.android.gms:play-services-auth:${BuildDependenciesVersions.playAuth}"
    const val firebaseCore = "com.google.firebase:firebase-core:${BuildDependenciesVersions.firebaseVersion}"
    const val playTagManager = "com.google.android.gms:play-services-tagmanager:${BuildDependenciesVersions.firebaseCrashlytics}"
    const val firebaseCM = "com.google.firebase:firebase-messaging:${BuildDependenciesVersions.fcm}"
    const val firebaseIAM = "com.google.firebase:firebase-inappmessaging-display:${BuildDependenciesVersions.firebaseInAppMessage}"

    const val googleLocation = "com.google.android.gms:play-services-location:${BuildDependenciesVersions.maps}"
    const val googlePlace = "com.google.android.libraries.places:places:${BuildDependenciesVersions.googlePlace}"
    const val maps = "com.google.android.gms:play-services-maps:${BuildDependenciesVersions.maps}"
    const val mapsKtx = "com.google.maps.android:maps-ktx:${BuildDependenciesVersions.mapsKtx}"
    const val mapsUtilsKtx = "com.google.maps.android:maps-utils-ktx:${BuildDependenciesVersions.mapsKtx}"

    const val activityKtx = "androidx.activity:activity-ktx:${BuildDependenciesVersions.activityKtx}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${BuildDependenciesVersions.fragmentKtx}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${BuildDependenciesVersions.lifecycleVersion}"
    const val lifecycleCoroutines = "androidx.lifecycle:lifecycle-livedata-ktx:${BuildDependenciesVersions.lifecycleVersion}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${BuildDependenciesVersions.lifecycleVersion}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${BuildDependenciesVersions.lifecycleVersion}"
    const val lifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:${BuildDependenciesVersions.lifecycleVersion}"

    // Coroutins
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${BuildDependenciesVersions.coroutinesVersion}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildDependenciesVersions.coroutinesAndroid}"
    const val retrofitCoroutines = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${BuildDependenciesVersions.coroutinesRetrofit}"

    // Components Manager
    const val componentManager = "com.github.valeryponomarenko.componentsmanager:androidx:${BuildDependenciesVersions.componentManager}"
}