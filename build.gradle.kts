// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Plugins.ANDROID_GRADLE)
    }
}

plugins {
    id(Plugins.Id.DETEKT).version(Plugins.Versions.DETEKT)
    kotlin(Plugins.Id.KOTLIN_ANDROID) version Kotlin.KOTLIN_VERSION apply false
}

allprojects {
    repositories {
        jcenter()
        google()
    }
}

val analysisDir = file(projectDir)
val configFile = file("$rootDir/detekt/detekt-rule-set.yml")

subprojects {
    apply(plugin = Plugins.Id.DETEKT)

    tasks {
        withType<io.gitlab.arturbosch.detekt.Detekt> {
            // Target version of the generated JVM bytecode. It is used for type resolution.
            this.jvmTarget = "1.8"
        }
        withType<Test> {
            maxParallelForks =
                (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
        }
    }

    detekt {
        input = files("src/main/kotlin", "src/test/kotlin", "src/androidTest/kotlin")
        config = files(configFile)
        ignoredBuildTypes = listOf("release")
        parallel = true
        autoCorrect = true
    }

    dependencies {
        detektPlugins(Plugins.DETEKT_FORMATTING)
    }
}

task("staticCheck") {
    description = "Runs all static checks."

    group = "verification"
    afterEvaluate {
        // Get modules with "testDebugUnitTest" task (app module does not have it)
        val testTasks = subprojects.mapNotNull { "${it.name}:testDebugUnitTest" }

        // All task dependencies
        val taskDependencies =
            mutableListOf("detekt").also {
                //  it.addAll(lintTasks)
                it.addAll(testTasks)
            }

        // By defining Gradle dependency all dependent tasks will run before this "empty" task
        dependsOn(taskDependencies)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
