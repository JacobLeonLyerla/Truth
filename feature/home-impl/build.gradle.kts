plugins {
    id(libs.plugins.sotAndroidConvetions.get().toString())
}

dependencies {
    api(projects.feature.homeApi)
}