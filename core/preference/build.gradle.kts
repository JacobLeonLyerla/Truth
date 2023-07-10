plugins {
    id(libs.plugins.sotAndroidConvetions.get().toString())
    kotlin("kapt")
}
android {
    namespace = "com.example.mytruth.core.preference"
}
dependencies {
    implementation(libs.datastore.preferences)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation ("androidx.security:security-crypto:1.1.0-alpha03")
}