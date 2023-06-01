plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id ("androidx.navigation.safeargs.kotlin")
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
    kotlinOptions {
        jvmTarget = "17"
    }
    externalNativeBuild {
        cmake {
            path("CMakeLists.txt")
        }
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
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}