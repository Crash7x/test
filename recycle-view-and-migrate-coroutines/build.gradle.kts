plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.android.navigation.safeargs)
}

android {
    namespace = "com.example.recycle.view.and.migrate.coroutines"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.recycle.view.and.migrate.coroutines"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField(
            "String",
            "URL_API",
            "\"https://rickandmortyapi.com/api/\""
        )
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.dagger)
    implementation(libs.retrofit)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.okhttp)
    implementation(libs.retrofit.kotlinx.serialization.converter)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.appcompat)
    implementation(libs.timber)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.navigation.dynamic.features.fragment)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.play.services.auth.api.phone)
    implementation(libs.play.services.auth)
    implementation(libs.coil)
    implementation(libs.coil.svg)
    kapt(libs.dagger.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}