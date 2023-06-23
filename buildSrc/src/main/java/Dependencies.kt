
object Deps {
    // AndroidX and Compose
    val androidxCore = "androidx.core:core-ktx:${Versions.coreKtx}"
    val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    val composeBom = "androidx.compose:compose-bom:${Versions.compose}"
    val composeUi = "androidx.compose.ui:ui"
    val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    val composeMaterial3 = "androidx.compose.material3:material3"

    // Network (Retrofit, Moshi)
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"

    // Dependency Injection (Hilt)
    val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}"

    // Database (Room)
    val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    // Image loading (Coil)
    val coilCompose = "io.coil-kt:coil-compose:${Versions.coil}"

    // Serialization
    val kotlinxSerializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinx}"

    // DataStore
    val datastorePreferences = "androidx.datastore:datastore-preferences:${Versions.datastore}"

    // Testing (MockK, JUnit, Coroutines Test)
    val mockk = "io.mockk:mockk:${Versions.mockk}"
    val junitBom = "org.junit:junit-bom:${Versions.junit}"
    val junitJupiter = "org.junit.jupiter:junit-jupiter:${Versions.junit}"
    val kotlinxCoroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
}