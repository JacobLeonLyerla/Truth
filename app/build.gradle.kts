plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    kotlin("android")
    kotlin("plugin.serialization")
    kotlin("kapt")
}

android {
    namespace = "com.example.mytruth"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.mytruth"
        minSdk = 31
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                @Suppress("UnstableApiUsage")
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    @Suppress("UnstableApiUsage")
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = "1.3.2"
    packagingOptions.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
}


dependencies {
    // AndroidX and Compose
    implementation(Deps.androidxCore)
    implementation(Deps.lifecycleRuntime)
    implementation(Deps.activityCompose)
    implementation(platform(Deps.composeBom))
    implementation(Deps.composeUi)
    implementation(Deps.composeUiGraphics)
    implementation(Deps.composeUiToolingPreview)
    implementation(Deps.composeMaterial3)

    // Network (Retrofit, Moshi)
    implementation(Deps.retrofit)
    implementation(Deps.retrofitConverterMoshi)
    implementation(Deps.moshiKotlin)

    // Dependency Injection (Hilt)
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)
    implementation(Deps.hiltNavigationCompose)

    // Database (Room)
    implementation(Deps.roomRuntime)
    implementation(Deps.roomKtx)
    kapt(Deps.roomCompiler)

    // Image loading (Coil)
    implementation(Deps.coilCompose)

    // Serialization
    implementation(Deps.kotlinxSerializationJson)

    // DataStore
    implementation(Deps.datastorePreferences)

    // Testing (MockK, JUnit, Coroutines Test)
    testImplementation(Deps.mockk)
    testImplementation(platform(Deps.junitBom))
    testImplementation(Deps.junitJupiter)
    testImplementation(Deps.kotlinxCoroutinesTest)
}


tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}