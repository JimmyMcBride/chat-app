plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("com.google.gms.google-services")
  id("kotlin-kapt")
}

android {
  namespace = "dev.jimmymcbride.chatapp"
  compileSdk = 33

  defaultConfig {
    applicationId = "dev.jimmymcbride.chatapp"
    minSdk = 26
    targetSdk = 33
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
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.4.3"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  implementation("androidx.core:core-ktx:1.10.1")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
  implementation("androidx.activity:activity-compose:1.7.2")
  implementation("androidx.compose.ui:ui:1.4.3")
  implementation("androidx.compose.ui:ui-graphics:1.4.3")
  implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
  implementation("androidx.compose.material3:material3:1.1.1")

  implementation(platform("com.google.firebase:firebase-bom:32.2.0"))
  implementation("com.google.firebase:firebase-auth-ktx")
//  implementation("com.google.firebase:firebase-firestore-ktx")
  implementation("com.google.android.gms:play-services-auth:20.6.0")
  implementation("androidx.navigation:navigation-compose:2.6.0")
  implementation("io.coil-kt:coil-compose:2.2.2")
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
  implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")


  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.3")
  debugImplementation("androidx.compose.ui:ui-tooling:1.4.3")
  debugImplementation("androidx.compose.ui:ui-test-manifest:1.4.3")
}
