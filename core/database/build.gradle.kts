plugins {
    id(libs.plugins.sotAndroidConvetions.get().toString())
    kotlin("kapt")
}
android {
    namespace = "com.example.mytruth.core.database"
}
dependencies {
    api(libs.bundles.room)
    kapt(libs.room.compiler)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}