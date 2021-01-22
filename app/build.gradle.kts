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

        javaCompileOptions {
            annotationProcessorOptions {
                arguments(
                    mapOf(
                        "dagger.gradle.incremental" to "true",
                        "room.schemaLocation" to "$projectDir/schemas",
                        "room.incremental" to "true",
                        "room.expandProjection" to "true"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        val options = this as? org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
        options?.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")

    lintOptions {
        isAbortOnError = false
    }
}

dependencies {
    //kotlin
    implementation(Kotlin.STD_LIB)

    implementation(Libs.MATERIAL)

    //rx
    implementation(Libs.RX_KOTLIN)
    implementation(Libs.RX_ANDROID)

    //retrofitx
    implementation(Libs.RETROFIT)
    implementation(Libs.RETROFIT_MOSHI_CONVERTER)
    implementation(Libs.RETROFIT_RX_ADAPTER)

    implementation(Libs.THREE_TEN_ABP)

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
    implementation(Libs.Testing.JUNIT)
}
