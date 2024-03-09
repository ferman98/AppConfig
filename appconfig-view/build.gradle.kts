import com.android.build.gradle.internal.tasks.factory.dependsOn

plugins {
    `maven-publish`
    alias(libs.plugins.gradlePlugin.library)
    alias(libs.plugins.gradlePlugin.kotlin)
}

tasks.register("filter") {
    doFirst {
        val f = File("$rootDir/appconfig-view/src/main/java/io/github/ferman98/appconfig/view/Test.kt")
        if(f.isDirectory)
        f.writeText("Ferman")
    }
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
    project.tasks.preBuild.dependsOn("filter")
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "io.github.ferman98.appconfig"
            artifactId = "view"
            version = "1.0.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
    repositories {
        maven {
            name = "myrepo"
            url = uri("${project.layout.buildDirectory}/repo")
        }
    }
}

tasks.register<Zip>("generateRepo") {
    val publishTask = tasks.named(
        "publishReleasePublicationToMyrepoRepository",
        PublishToMavenRepository::class.java
    )
    from(publishTask.map { it.repository.url })
    into("mylibrary")
    archiveFileName.set("mylibrary.zip")
}

dependencies {
    implementation(project(":appconfig-internal"))
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)
    implementation(libs.kotlin.reflection)
    implementation(libs.google.gson)
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.junitAndroid)
    androidTestImplementation(libs.test.espresso)
}