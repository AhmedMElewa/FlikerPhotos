object Hilt {
    const val hiltAndroid = "com.google.dagger:hilt-android:2.45"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:2.45"
}
object Coroutines {
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4"
}
object Lifecycle {
    const val coreLifeCycle = "androidx.core:core-ktx:1.8.0"
    const val fragmentLifeCycle = "androidx.fragment:fragment-ktx:1.5.1"
    const val activityLifeCycle = "androidx.activity:activity-ktx:1.5.1"
    const val runtimeLifeCycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
    const val livedataLifeCycle = "androidx.lifecycle:lifecycle-livedata-ktx:2.5.1"
    const val viewmodelLifeCycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1"

}

object Utils {
    const val timber = "com.jakewharton.timber:timber:5.0.1"
}

object AndroidUI {
    const val appCompact = "androidx.appcompat:appcompat:1.4.2"
    const val materialDesign = "com.google.android.material:material:1.6.1"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"
}
object Retrofit {
    const val retrofit_version = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:$retrofit_version"
}
object OkHttp {
    const val ok_http_version = "4.9.3"
    const val OkHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:$ok_http_version"
}
object Room {
    const val room_version = "2.4.3"
    const val roomRuntime = "androidx.room:room-runtime:$room_version"
    const val roomKtx = "androidx.room:room-ktx:$room_version"
    const val roomKapt = "androidx.room:room-compiler:$room_version"
    const val roomPagging = "androidx.room:room-paging:$room_version"
}
object Pagging {
    const val paging_version = "3.1.1"
    const val pagging = "androidx.paging:paging-runtime:$paging_version"
}
object Navigation {
    const val nav_version = "2.5.3"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$nav_version"
    const val navigation = "androidx.navigation:navigation-ui-ktx:$nav_version"
}
object Glide {
    const val glide_version = "4.13.2"
    const val glide =  "com.github.bumptech.glide:glide:$glide_version"
    const val glideKapt=  "com.github.bumptech.glide:compiler:$glide_version"
}
