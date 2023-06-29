@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    kotlin("android")
}

version = "1.0.0-ALPHA"

android {
    val compile = extra["android.compileSdk"] as String
    val min = extra["android.minSdk"] as String

    compileSdk = compile.toInt()

    defaultConfig {
        minSdk = min.toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }
    configurations {
        create("cleanedAnnotations")

        implementation.get().exclude(group = "org.jetbrains", module = "annotations")
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

    packagingOptions.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
}

