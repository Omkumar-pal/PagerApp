plugins {
    alias(libs.plugins.android.application)
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.paggerapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.paggerapp"
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
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.paging.common.android)
    implementation(libs.leanback.paging)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    //Hilt
    implementation ("com.google.dagger:hilt-android:2.44")
    annotationProcessor ("com.google.dagger:hilt-android-compiler:2.44")
    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.google.code.gson:gson:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
    //Rxjava3 Adapter and dependency
    //"androidx.paging:paging-rxjava3:3.1.1"
    implementation ("com.squareup.retrofit2:adapter-rxjava3:2.11.0")
    implementation ("io.reactivex.rxjava3:rxjava:3.1.8")
    // Paging library
    implementation("androidx.paging:paging-rxjava3:3.3.0")
    implementation ("com.google.code.gson:gson:2.11.0")
    implementation ("com.google.code.gson:gson:2.11.0")

    // Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")

    //LifeCyle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel:2.6.2") // ViewModel
    implementation ("androidx.lifecycle:lifecycle-livedata:2.6.2") // LiveData
    implementation ("androidx.lifecycle:lifecycle-common-java8:2.6.2")

}