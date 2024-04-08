buildscript {
    repositories {
        mavenCentral()
        google()
    }

    dependencies {
        classpath(libs.android.gradlePlugin)
        classpath(libs.kotlin.gradlePlugin)
        classpath(libs.kotlin.serialization)
        classpath(libs.navigation.safe.args.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply true
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.android.navigation.safeargs) apply false
}