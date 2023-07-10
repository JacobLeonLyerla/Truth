@file:Suppress("UnstableApiUsage")

plugins {
    id(libs.plugins.sotAndroidConvetions.get().toString())
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.example.mytruth.feature.home"
    val composeCompiler = extra["compose.compiler"] as String


    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = composeCompiler
}

dependencies {
    implementation(projects.feature.homeApi)
    implementation(projects.core.database)
    implementation(projects.core.preference)
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