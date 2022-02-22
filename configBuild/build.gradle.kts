plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

//group = "com.husseinrasti.coinecho"
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
    const val DAGGER_HILT = "2.39"
    const val NAVIGATION = "2.3.5"
}

dependencies {
    implementation("com.android.tools.build:gradle:${PluginsVersions.GRADLE_ANDROID}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:${PluginsVersions.DOKKA}")
    implementation("com.google.dagger:hilt-android-gradle-plugin:${PluginsVersions.DAGGER_HILT}")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginsVersions.NAVIGATION}")
}


gradlePlugin {
    plugins {
        create("coinecho-config") {
            id = "coinecho-config"
            implementationClass = "com.husseinrasti.coinecho.CoinEchoPlugin"
        }
        create("coinecho-android-library") {
            id = "coinecho-android-library"
            implementationClass = "com.husseinrasti.coinecho.plugins.AndroidLibraryPlugin"
        }
    }
}