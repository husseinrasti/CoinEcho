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

package com.husseinrasti.build_core

import com.android.build.gradle.internal.dsl.ProductFlavor
import com.husseinrasti.extensions.android
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra


fun Project.configureApplicationFlavor() {
    android {
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


        flavorDimensions.add(BuildProductDimensions.ENVIRONMENT)
        productFlavors.apply {
            ProductFlavorDevelop.appCreate(this)
            ProductFlavorProduction.appCreate(this)
        }
    }
}

fun Project.configureLibraryFlavor() {
    android {
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


        flavorDimensions.add(BuildProductDimensions.ENVIRONMENT)
        productFlavors.apply {
            ProductFlavorDevelop.libraryCreate(this)
            ProductFlavorProduction.libraryCreate(this)
        }
    }
}

interface BuildProductFlavor {
    val name: String

    fun libraryCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor

    fun appCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor
}

object ProductFlavorDevelop : BuildProductFlavor {
    override val name = "develop"

    override fun appCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor {
        return namedDomainObjectContainer.create(name) {
            applicationIdSuffix = ".develop"
            versionNameSuffix = "-dev"
            dimension = BuildProductDimensions.ENVIRONMENT
        }
    }

    override fun libraryCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor {
        return namedDomainObjectContainer.create(name) {
            dimension = BuildProductDimensions.ENVIRONMENT
        }
    }
}

object ProductFlavorProduction : BuildProductFlavor {
    override val name = "production"

    override fun appCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor {
        return namedDomainObjectContainer.create(name) {
            dimension = BuildProductDimensions.ENVIRONMENT
        }
    }

    override fun libraryCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor {
        return namedDomainObjectContainer.create(name) {
            dimension = BuildProductDimensions.ENVIRONMENT
        }
    }
}
