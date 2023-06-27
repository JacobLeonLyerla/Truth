import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

apply(from ="../gradle/loadProps.gradle")

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    gradlePluginPortal()
}

dependencies {
    //  val agpVersion = "8.0.2"
    val agpVersion = project.properties["agp.version"] as String
    val kotlinVersion = "1.8.21"
    implementation("com.android.tools.build:gradle:$agpVersion")
    implementation("org.jetbrains.kotlin.android:org.jetbrains.kotlin.android.gradle.plugin:$kotlinVersion")
    implementation("com.squareup:javapoet:1.13.0")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
}

tasks.withType<JavaCompile>() {
    sourceCompatibility = JavaVersion.VERSION_17.toString()
    targetCompatibility = JavaVersion.VERSION_17.toString()
}
