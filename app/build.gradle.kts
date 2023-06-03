plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id ("androidx.navigation.safeargs.kotlin")
    id ("kotlin-parcelize")
}

android {
    namespace = "com.elewa.flikerphotos"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = "com.elewa.flikerphotos"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = "com.elewa.flikerphotos.HiltTestRunner"
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
    externalNativeBuild {
        cmake {
            path("CMakeLists.txt")
        }
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    // -------------------------------------- Hilt -------------------------------
    implementation(Hilt.hiltAndroid)
    kapt(Hilt.hiltCompiler)

    // timber
    implementation(Utils.timber)

    // glide
    implementation(Glide.glide)
    kapt(Glide.glideKapt)

    // navigation
    implementation(Navigation.navigation)
    implementation(Navigation.navigationFragment)

    // retrofit
    implementation(Retrofit.retrofit)
    implementation(Retrofit.retrofitGson)
    // okhttp
    implementation(OkHttp.OkHttpInterceptor)
    // room
    implementation(Room.roomRuntime)
    implementation(Room.roomKtx)
    kapt(Room.roomKapt)
    implementation(Room.roomPagging)
    // paging
    implementation(Pagging.pagging)

    // -------------------------------------------- lifecycle -------------------------------
    implementation(Lifecycle.coreLifeCycle)
    implementation(Lifecycle.fragmentLifeCycle)
    implementation(Lifecycle.activityLifeCycle)
    implementation(Lifecycle.runtimeLifeCycle)
    implementation(Lifecycle.livedataLifeCycle)
    implementation(Lifecycle.viewmodelLifeCycle)

    // ------------ android ui-------------------------------------
    implementation(AndroidUI.appCompact)
    implementation(AndroidUI.materialDesign)
    implementation(AndroidUI.constraintLayout)

    // ------------ testing -------------------------------------
    // Local Unit Tests
    testImplementation (Test.junit)
    testImplementation (Test.hamcrest)
    testImplementation (Test.testingCore)
    testImplementation (Test.robolectric)
    testImplementation (Test.testingCoroutines)
    testImplementation (Test.truth)
    testImplementation (Test.mockito)
    testImplementation (Test.hilt)
    kaptTest (Test.kaptHilt)

    // Instrumented Unit Tests
    androidTestImplementation (Test.junit)
    androidTestImplementation (Test.testingCoroutines)
    androidTestImplementation (Test.testingCore)
    androidTestImplementation (Test.truth)
    androidTestImplementation (Test.junitExt)
    androidTestImplementation (Test.espresso)
    androidTestImplementation (Test.mockito)
    androidTestImplementation (Test.hilt)
    kaptAndroidTest (Test.kaptHilt)
}