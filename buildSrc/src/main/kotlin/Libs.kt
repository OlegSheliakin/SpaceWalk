object Libs {
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val LIFECYCLE_EXTENSION =
        "androidx.lifecycle:lifecycle-extensions:${Versions.LIFECYCLE_VERSION}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val RX_KOTLIN = "io.reactivex.rxjava2:rxkotlin:${Versions.RX_KOTLIN}"
    const val RX_RELAY = "com.jakewharton.rxrelay2:rxrelay:${Versions.RX_RELAY}"
    const val RX_ANDROID = "io.reactivex.rxjava2:rxandroid:${Versions.RX_ANDROID}"
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
    const val ROOM_RX = "androidx.room:room-rxjava2:${Versions.ROOM}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_RX_ADAPTER = "com.squareup.retrofit2:adapter-rxjava2:${Versions.RETROFIT}"

    const val OK_HTTP_LOGGING_INTERCEPTOR =
        "com.squareup.okhttp3:logging-interceptor:${Versions.OK_HTTP}"
    const val DAGGER_RUNTIME = "com.google.dagger:dagger:${Versions.DAGGER}"
    const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"
    const val NAVIGATION_FRAGMENT =
        "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"
    const val CORE_DESUGARING_LIBRARY =
        "com.android.tools:desugar_jdk_libs:${Versions.CORE_DESUGARING_LIBRARY}"
    const val SWIPE_TO_REFRESH =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.SWIPE_TO_REFRESH}"

    object Moshi {
        const val RUNTIME = "com.squareup.moshi:moshi:${Versions.MOSHI}"
        const val RETROFIT_MOSHI_CONVERTER =
            "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT}"
        const val KAPT = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.MOSHI}"
    }

    object Testing {
        const val JUNIT_API = "org.junit.jupiter:junit-jupiter-api:${Versions.JUNIT}"
        const val JUNIT_ENGINE = "org.junit.jupiter:junit-jupiter-engine:${Versions.JUNIT}"
        const val MOCK_WEB_SERVER = "com.squareup.okhttp3:mockwebserver:${Versions.OK_HTTP}"
        const val ANDROID_TEST_RUNNER = "androidx.test:runner:${Versions.ANDROID_TEST_RUNNER}"
        const val ANDROID_JUNIT_5_CORE = "de.mannodermaus.junit5:android-test-core:${Versions.ANDROID_JUNIT_5}"
        const val ANDROID_JUNIT_5_RUNNER = "de.mannodermaus.junit5:android-test-runner:${Versions.ANDROID_JUNIT_5}"
    }

    private object Versions {
        const val APPCOMPAT = "1.2.0"
        const val CONSTRAINT_LAYOUT = "1.1.3"
        const val CORE_KTX = "1.3.2"
        const val MATERIAL = "1.2.1"
        const val JUNIT = "5.7.0"
        const val ANDROID_TEST_RUNNER = "1.2.0"
        const val ANDROID_JUNIT_5 = "1.2.0"
        const val RETROFIT = "2.9.0"
        const val OK_HTTP = "4.9.0"
        const val ROOM = "2.2.3"
        const val DAGGER = "2.31"
        const val RX_KOTLIN = "2.4.0"
        const val RX_RELAY = "2.1.1"
        const val RX_ANDROID = "2.1.1"
        const val LIFECYCLE_VERSION = "2.1.0"
        const val NAVIGATION = "2.1.0"
        const val TIMBER = "4.7.1"
        const val CORE_DESUGARING_LIBRARY = "1.0.9"
        const val SWIPE_TO_REFRESH = "1.0.0"
        const val MOSHI = "1.11.0"
    }
}
