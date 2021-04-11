object Dependencies {
    const val androidXCore = "androidx.core:core-ktx:${Versions.androidXCore}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val annotation = "androidx.annotation:annotation:${Versions.annotationVersion}"

    const val multidex = "androidx.multidex:multidex:${Versions.multidexVersion}"

    const val cardView = "androidx.cardview:cardview:${Versions.cardViewVersion}"
    const val circleImageView = "de.hdodenhof:circleimageview:${Versions.circleImageViewVersion}"
    const val circleIndicator = "me.relex:circleindicator:${Versions.circleIndicatorVersion}"

    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
    const val espressoIntents =
            "androidx.test.espresso:espresso-intents:${Versions.espressoIntentsVersion}"

    const val hirondelleDate = "com.darwinsys:hirondelle-date4j:${Versions.hirondelleDateVersion}"

    const val jsr = "javax.annotation:jsr250-api:${Versions.jsrVersion}"

    const val keyboardVisibilityEvent =
            "net.yslibrary.keyboardvisibilityevent:keyboardvisibilityevent:${Versions.keyboardVisibilityEventVersion}"

    //kotlin
    const val kotlinJdk = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlinVersion}"

    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupportVersion}"
    const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"

    //lifecycle
    const val lifeCycleCompiler =
            "androidx.lifecycle:lifecycle-compiler:${Versions.lifeCycleVersion}"
    const val lifeCycleExtension =
            "androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycleVersion}"
    const val lifeCycleViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycleVersion}"
    const val lifeCycleLiveData =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycleVersion}"
    const val lifeCycleRuntime =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycleVersion}"
    const val lifeCycle =
            "androidx.lifecycle:lifecycle-common-java8:${Versions.lifeCycleVersion}"

    //material
    const val material = "com.google.android.material:material:${Versions.materialVersion}"

    //mockito
    const val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockitoAndroidVersion}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoCoreVersion}"

    const val testRules = "androidx.test:rules:${Versions.testRulesVersion}"
    const val testRunner = "androidx.test:runner:${Versions.testRunnerVersion}"
    const val uCrop = "com.yalantis:ucrop:${Versions.uCropVersion}"

    const val stateLayout = "com.github.erkutaras:StateLayout:${Versions.stateLayoutVersion}"

    //test
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val truth = "com.google.truth:truth:${Versions.truthVersion}"
    const val truthExt = "androidx.test.ext:truth:${Versions.truthExtVersion}"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExtVersion}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectricVersion}"

    //firebase
    const val firebaseBoM = "com.google.firebase:firebase-bom:${Versions.firebaseBoMVersion}"
    const val firebaseMessaging = "com.google.firebase:firebase-messaging-ktx"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    const val firebaseDb = "com.google.firebase:firebase-database-ktx"

    // dagger
    const val dagger = "com.google.dagger:dagger-android:${Versions.daggerVersion}"
    const val daggerAndroidSupport =
        "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
    const val daggerAndroidProcessor =
        "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"

    //chucker
    const val chucker = "com.github.chuckerteam.chucker:library:${Versions.chuckerVersion}"
    const val chuckerNoOp =
            "com.github.chuckerteam.chucker:library-no-op:${Versions.chuckerVersion}"

    // network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val logInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"
    const val gsonConverter =
            "com.squareup.retrofit2:converter-gson:${Versions.gsonConverterVersion}"

    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"

    const val retrofitRxAdapter =
            "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofitVersion}"

    const val gson = "com.google.code.gson:gson:2.8.6"

    const val rxJava = "io.reactivex.rxjava3:rxjava:${Versions.rxJavaVersion}"
    const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxAndroidVersion}"
    const val rxBindingMaterial =
            "com.jakewharton.rxbinding4:rxbinding-material:${Versions.rxBindingVersion}"

    //glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"

    //room
    const val roomRxJava = "androidx.room:room-rxjava3:${Versions.roomVersion}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"

    //preferences
    const val preference = "androidx.preference:preference-ktx:${Versions.preferencesVersion}"

    //medusa
    const val medusa = "com.github.trendyol:medusa:${Versions.medusaVersion}"


    //utility
    const val ssp = "com.intuit.ssp:ssp-android:${Versions.intuitVersion}"
    const val sdp = "com.intuit.sdp:sdp-android:${Versions.intuitVersion}"

    //timber
    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"

    // threeTenABP
    const val treeTenABP = "com.jakewharton.threetenabp:threetenabp:${Versions.treeTenABPVersion}"

    //paging
    const val paging = "androidx.paging:paging-runtime:${Versions.pagingVersion}"

    //swipe refresh layout
    const val swipeRefreshLayout =
            "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayoutVersion}"
}