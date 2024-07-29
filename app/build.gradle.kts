plugins {
    // Application
    alias(libs.plugins.android.application)

    // Kotlin Android
    alias(libs.plugins.jetbrains.kotlin.android)

    // Kapt
    id("kotlin-kapt")

    // Hilt
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.marsel.todo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.marsel.todo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        // ViewBinding
        viewBinding = true
    }
}

dependencies {

    // Core
    implementation(libs.androidx.core.ktx)

    // App Compat
    implementation(libs.androidx.appcompat)

    // Material
    implementation(libs.material)

    // Activity
    implementation(libs.androidx.activity)

    // Constraint Layout
    implementation(libs.androidx.constraintlayout)

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Navigation Component
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui.ktx)

    // DotsIndicator
    implementation(libs.dotsindicator)

    // ViewPager2
    implementation(libs.androidx.viewpager2)

    // Datetime
    implementation(libs.kotlinx.datetime)

    // Data
    implementation(project(":data"))

    // Domain
    implementation(project(":domain"))

    // View binding property delegate
    implementation(libs.view.binding.property.delegate)
}