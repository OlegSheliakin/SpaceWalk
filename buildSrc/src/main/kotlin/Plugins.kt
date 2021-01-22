object Plugins {
    const val ANDROID_GRADLE = "com.android.tools.build:gradle:${Versions.AGP}"
    const val DETEKT_FORMATTING = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.DETEKT}"

    object Id {
        const val ANDROID_APPLICATION = "com.android.application"
        const val ANDROID_LIBRARY = "com.android.library"
        const val KOTLIN_ANDROID = "android"
        const val DETEKT = "io.gitlab.arturbosch.detekt"
        const val KAPT = "kapt"
    }

    object Versions {
        const val AGP = "4.1.2"
        const val DETEKT = "1.14.2"
    }
}