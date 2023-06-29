plugins {
    id(libs.plugins.sotAndroidConvetions.get().toString())
    kotlin("kapt")
}
android {
    namespace = "com.example.mytruth.core.network"
}
dependencies {
    api(libs.bundles.network)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}