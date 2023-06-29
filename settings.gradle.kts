@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        val agpVersion = extra["agp.version"] as String
        id("com.android.application").version(agpVersion)
        id("com.android.library").version(agpVersion)
        kotlin("android").version("1.8.21")
    }

}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MyTruth"
include(
    ":app",
    ":core:feature-api",
    ":core:network",
    ":core:database",
    ":feature:home-api",
    ":feature:home-impl"
)
