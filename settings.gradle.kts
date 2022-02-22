rootProject.name = "CoinEcho"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

includeBuild("configBuild")

include(":app")
