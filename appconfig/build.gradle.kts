plugins {
    alias(libs.plugins.gradlePlugin.library)
    alias(libs.plugins.gradlePlugin.kotlin)
}

android {
    namespace = "io.github.ferman98.appconfig"
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
    project.tasks.preBuild.dependsOn("filter")
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":appconfig-internal"))
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)
    implementation(libs.kotlin.reflection)
    implementation(libs.google.gson)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.view.constraintlayout)
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.junitAndroid)
    androidTestImplementation(libs.test.espresso)
}