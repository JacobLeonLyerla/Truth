@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    kotlin("android")
    kotlin("plugin.serialization")
    kotlin("kapt")
}

android {
    val compile = extra["android.compileSdk"] as String
    val target = extra["android.targetSdk"] as String
    val min = extra["android.minSdk"] as String
    val composeCompiler = extra["compose.compiler"] as String

    namespace = "com.example.mytruth"
    compileSdk = compile.toInt()

    defaultConfig {
        applicationId = "com.example.mytruth"
        minSdk = min.toInt()
        targetSdk = target.toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
        freeCompilerArgs =
            freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
    }
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = composeCompiler
    packagingOptions.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
}


dependencies {

    implementation(projects.core.featureApi)
    implementation(projects.feature.homeApi)
    implementation(projects.feature.homeImpl)

    // AndroidX and Compose
    implementation(Deps.androidxCore)
//    implementation(libs.androidx)

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