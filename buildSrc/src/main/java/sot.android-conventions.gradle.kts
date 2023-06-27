@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    kotlin("android")
}

version = "1.0.0-ALPHA"

android {
    val compile = extra["android.compileSdk"] as String
    val min = extra["android.minSdk"] as String
    val composeCompiler = extra["compose.compiler"] as String

    namespace = "com.example.mytruth"
    compileSdk = compile.toInt()

    defaultConfig {
        minSdk = min.toInt()

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
    }
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = composeCompiler
    packagingOptions.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
}

