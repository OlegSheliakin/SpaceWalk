plugins {
    id(Plugins.Id.ANDROID_APPLICATION)
    kotlin(Plugins.Id.KOTLIN_ANDROID)
    kotlin(Plugins.Id.KAPT)
}

repositories {
    mavenCentral()
}

android {
    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        applicationId = AndroidConfig.ID
        minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)

        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME
        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER
        testInstrumentationRunnerArgument(AndroidConfig.RUNNER_BUILDER, AndroidConfig.JUNIT_5_BUILDER)
        javaCompileOptions {
            annotationProcessorOptions {
                arguments(
                    mapOf(
                        "room.incremental" to "true"
                    )
                )
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        val options = this as? org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
        options?.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")
    sourceSets["test"].java.srcDir("src/test/kotlin")
    sourceSets["androidTest"].java.srcDir("src/androidTest/kotlin")

    lintOptions {
        isAbortOnError = false
    }

    packagingOptions {
        exclude("META-INF/LICENSE.md")
        exclude("META-INF/LICENSE-notice.md")
    }
}

dependencies {
    //kotlin
    implementation(Kotlin.STD_LIB)

    implementation(Libs.MATERIAL)

    //rx
    implementation(Libs.RX_KOTLIN)
    implementation(Libs.RX_ANDROID)
    implementation(Libs.RX_RELAY)

    //retrofit
    implementation(Libs.RETROFIT)
    implementation(Libs.RETROFIT_RX_ADAPTER)

    //moshi
    implementation(Libs.Moshi.RUNTIME)
    implementation(Libs.Moshi.RETROFIT_MOSHI_CONVERTER)
    kapt(Libs.Moshi.KAPT)

    coreLibraryDesugaring(Libs.CORE_DESUGARING_LIBRARY)

    //logging
    implementation(Libs.OK_HTTP_LOGGING_INTERCEPTOR)
    implementation(Libs.TIMBER)

    //dagger
    implementation(Libs.DAGGER_RUNTIME)
    kapt(Libs.DAGGER_COMPILER)

    //room
    implementation(Libs.ROOM_RUNTIME)
    implementation(Libs.ROOM_RX)
    kapt(Libs.ROOM_COMPILER)

    //testing
    testRuntimeOnly(Libs.Testing.JUNIT_ENGINE)
    testImplementation(Libs.Testing.JUNIT_API)
    testImplementation(Libs.Testing.MOCK_WEB_SERVER)

    //android testing
    androidTestImplementation(Libs.Testing.JUNIT_API)
    androidTestImplementation(Libs.Testing.ANDROID_TEST_RUNNER)
    androidTestImplementation(Libs.Testing.ANDROID_JUNIT_5_CORE)
    androidTestRuntimeOnly(Libs.Testing.ANDROID_JUNIT_5_RUNNER)
}
