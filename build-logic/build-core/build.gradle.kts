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

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

//group = "com.husseinrasti"
//version = "1.0.0"

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
    implementation(project(":libs"))
}

gradlePlugin {
    plugins {
        create("core-plugin") {
            id = "core-plugin"
            implementationClass = "com.husseinrasti.BuildCorePlugin"
        }
        create("core-android-library") {
            id = "core-android-library"
            implementationClass = "com.husseinrasti.plugins.AndroidLibraryConventionPlugin"
        }
        create("coreAndroidLibrary") {
            id = "build.logic.android.library"
            implementationClass = "com.husseinrasti.plugins.AndroidLibraryConventionPlugin"
        }
        create("buildLogicLibraryCompose") {
            id = "build.logic.android.library.compose"
            implementationClass = "com.husseinrasti.plugins.AndroidComposeConventionPlugin"
        }
        create("buildLogicLibraryFeature") {
            id = "build.logic.android.library.feature"
            implementationClass = "com.husseinrasti.plugins.AndroidFeatureConventionPlugin"
        }
        create("buildLogicLibraryHilt") {
            id = "build.logic.android.library.hilt"
            implementationClass = "com.husseinrasti.plugins.AndroidHiltConventionPlugin"
        }
    }
}