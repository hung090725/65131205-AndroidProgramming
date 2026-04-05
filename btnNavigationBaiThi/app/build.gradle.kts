import org.gradle.api.tasks.compile.JavaCompile

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "hung.edu.chbaithi"
    compileSdk = 36

    defaultConfig {
        applicationId = "hung.edu.chbaithi"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

// Cursor/VS Code Java (Red Hat) can point compilation at a minimal embedded JRE
// (no jlink). Force the same full JDK as org.gradle.java.home / jdk.path.
val jdkPath = (findProperty("jdk.path") as String?)
    ?: "C:/Program Files/Android/Android Studio/jbr"

tasks.withType<JavaCompile>().configureEach {
    options.isFork = true
    options.forkOptions.javaHome = file(jdkPath)
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}