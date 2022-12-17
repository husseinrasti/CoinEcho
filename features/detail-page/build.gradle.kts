import com.husseinrasti.build_core.BuildModules

plugins {
    id("core-android-library")
    id("org.jetbrains.kotlin.android")
}

dependencies {
    implementation(project(BuildModules.CORE))
    implementation(project(BuildModules.Core.MODEL))
    implementation(project(BuildModules.DOMAIN))

    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
}