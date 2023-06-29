plugins {
    id(libs.plugins.sotAndroidConvetions.get().toString())
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.example.mytruth.feature.home"
}

dependencies {
    implementation(projects.feature.homeApi)
    implementation(libs.bundles.room)
    implementation(libs.datastore.preferences)
    implementation(libs.bundles.network)
    implementation(libs.hilt.android)
    kapt(libs.room.compiler)
    kapt(libs.hilt.compiler)

    testImplementation(libs.bundles.test)
}
tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}