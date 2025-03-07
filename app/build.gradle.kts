plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.app_san_luis_gonzaga"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.app_san_luis_gonzaga"
        minSdk = 29
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("androidx.activity:activity:1.10.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    // Room Database
    implementation("androidx.room:room-runtime:2.5.0") // Room Core
    annotationProcessor("androidx.room:room-compiler:2.5.0") // Para procesamiento de anotaciones (para versiones anteriores a Room 2.6.0)

    // Para usar con Kotlin (si es necesario)
    implementation("androidx.room:room-ktx:2.5.0")

    // Dependencia para pruebas (opcional)
    testImplementation("androidx.room:room-testing:2.5.0")

}