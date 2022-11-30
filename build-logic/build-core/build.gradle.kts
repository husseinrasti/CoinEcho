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

object PluginsVersions {
    const val GRADLE_ANDROID = "7.0.4"
    const val KOTLIN = "1.6.10"
    const val DOKKA = "0.10.0"
    const val DAGGER_HILT = "2.42"
    const val NAVIGATION = "2.3.5"
}

dependencies {
    implementation("com.android.tools.build:gradle:${PluginsVersions.GRADLE_ANDROID}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:${PluginsVersions.DOKKA}")
    implementation("com.google.dagger:hilt-android-gradle-plugin:${PluginsVersions.DAGGER_HILT}")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginsVersions.NAVIGATION}")
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
            implementationClass = "com.husseinrasti.plugins.AndroidLibraryPlugin"
        }
    }
}