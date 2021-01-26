object Plugins {
    const val ANDROID_GRADLE = "com.android.tools.build:gradle:${Versions.AGP}"
    const val DETEKT_FORMATTING = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.DETEKT}"
    const val ANDROID_JUNIT_5 =
        "de.mannodermaus.gradle.plugins:android-junit5:${Versions.ANDROID_JUNIT_5}"

    object Id {
        const val ANDROID_APPLICATION = "com.android.application"
        const val KOTLIN_ANDROID = "android"
        const val DETEKT = "io.gitlab.arturbosch.detekt"
        const val ANDROID_JUNIT_5 = "de.mannodermaus.android-junit5"
        const val KAPT = "kapt"
    }

    object Versions {
        const val AGP = "4.1.2"
        const val DETEKT = "1.14.2"
        const val ANDROID_JUNIT_5 = "1.7.0.0"
    }
}