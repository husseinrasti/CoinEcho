import com.husseinrasti.build_core.BuildModules

plugins {
    id("core-android-library")
    id("org.jetbrains.kotlin.android")
}



dependencies {
    implementation(project(BuildModules.CORE))
    implementation(project(BuildModules.Core.MODEL))
    implementation(project(BuildModules.DOMAIN))


   /* implementation ("com.jjoe64:graphview:4.2.2")*/

    /*implementation ("com.androidplot:androidplot-core:1.5.7")*/

   implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")

  /*  implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")*/

}