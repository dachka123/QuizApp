plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "eu.tutorials.quizapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "eu.tutorials.quizapp"
        minSdk = 24
        targetSdk = 35
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
}

dependencies {
    implementation("com.google.android.material:material:1.10.0-alpha01")

    implementation("com.karumi:dexter:6.0.1")

    implementation("im.dino:dbinspector:3.4.1@aar")

    implementation("de.hdodenhof:circleimageview:3.1.0")

    implementation("com.google.android.libraries.places:places:4.1.0")
    implementation ("com.google.android.gms:play-services-maps:18.2.0")

    implementation ("com.google.android.gms:play-services-location:17.0.0")

    implementation ("com.google.code.gson:gson:2.8.9")


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.ui.graphics.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}