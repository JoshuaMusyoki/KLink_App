plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.googleDevToolsKsp)
    alias(libs.plugins.daggerHiltAndroid)
//    alias(libs.plugins.googleGmsServices)
}

android {
    namespace = "com.example.songabiz"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.songabiz"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
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
//    KTOR
    implementation("io.ktor:ktor-client-auth:${libs.versions.ktor.client.auth.get()}")
    implementation("io.ktor:ktor-client-core:${libs.versions.ktor.client.core.get()}")
    implementation("io.ktor:ktor-client-cio:${libs.versions.ktor.client.cio.get()}")

//    Navigation Compose
//    implementation(libs.androidx.navigation.compose)

//Material Design
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.extended.icon)
//    Android Core
    implementation(libs.androidx.core.ktx)
//    Splash Screen
    implementation(libs.androidx.core.splashscreen)
//    Android Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
//    Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
//    UI
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
//    dagger hilt
    implementation(libs.dagger.hilt)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.navigation.compose)
    implementation(libs.play.services.maps)
    ksp(libs.dagger.hilt.compiler)

    //coroutines
    implementation(libs.kotlinx.coroutines.android)
    //coil
    implementation(libs.coil.compose)
    //datastore
    implementation(libs.androidx.datastore)

    implementation(libs.androidx.material3)
    implementation(libs.firebase.crashlytics.buildtools)
    //    Local/Unit Test
    testImplementation(libs.junit)
    testImplementation(libs.mockito.kotlin)

    //Retrofit
    implementation ("com.squareup.retrofit2:converter-gson:2.5.0")

//    Maps
//    implementation ("com.google.maps.android:maps-compose:3.4.0")

    // Google maps
    implementation ("com.google.android.gms:play-services-location:21.0.1")
    // Google maps for compose
    implementation ("com.google.maps.android:maps-compose:2.8.0")
    implementation ("com.google.android.gms:play-services-tasks:18.0.2")
// Permissions
    implementation("pub.devrel:easypermissions:3.0.0")
    implementation ("androidx.activity:activity-compose:1.4.0-alpha01")
    implementation ("com.google.accompanist:accompanist-permissions:0.20.0")
//    Instrumentation Test
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

