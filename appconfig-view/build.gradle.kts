plugins {
    alias(libs.plugins.gradlePlugin.library)
    alias(libs.plugins.gradlePlugin.kotlin)
}

android {
    namespace = "io.github.ferman98.appconfig.view"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    implementation(project(":appconfig-internal"))
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)
    implementation(libs.kotlin.reflection)
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.junitAndroid)
    androidTestImplementation(libs.test.espresso)
}