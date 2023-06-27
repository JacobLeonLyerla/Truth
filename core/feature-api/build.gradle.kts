plugins {
    id(libs.plugins.sotAndroidConvetions.get().toString())
}

dependencies {
    //todo set up api if this breaks
    implementation(platform(libs.composeBom))

    api(libs.bundles.compose)
}