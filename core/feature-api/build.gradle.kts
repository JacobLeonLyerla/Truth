
plugins {
    id(libs.plugins.sotAndroidConvetions.get().toString())
}

android {
    namespace = "com.example.mytruth.core.feature.api"
}

dependencies {
    //todo set up api if this breaks
    api(platform(libs.compose.bom))
    api(libs.bundles.compose)
}