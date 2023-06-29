plugins {
    id(libs.plugins.sotAndroidConvetions.get().toString())
}
android {
    namespace = "com.example.mytruth.feature.api.home"
}
dependencies {
    api(projects.core.featureApi)
}