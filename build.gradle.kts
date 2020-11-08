// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.ANDROID_GRADLE_PLUGIN}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}")
        classpath("com.google.gms:google-services:${Versions.GOOGLE_SERVICES}")
        classpath("androidx.benchmark:benchmark-gradle-plugin:${Versions.BENCHMARK}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION}")
        classpath("com.google.firebase:firebase-crashlytics-gradle:${Versions.CRASHLYTICS_GRADLE_PLUGIN}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}")
    }
}

plugins {
    id("com.diffplug.gradle.spotless") version "3.27.1"
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}