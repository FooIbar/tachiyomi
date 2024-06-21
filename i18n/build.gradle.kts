plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    androidTarget()
    sourceSets {
        commonMain {
            dependencies {
                api(libs.moko.core)
            }
        }
    }
}

android {
    namespace = "tachiyomi.i18n"

    lint {
        disable.addAll(listOf("MissingTranslation", "ExtraTranslation"))
    }
}

multiplatformResources {
    resourcesPackage = "tachiyomi.i18n"
}

tasks {
    val localesConfigTask = registerLocalesConfigTask(project)
    preBuild {
        dependsOn(localesConfigTask)
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions.freeCompilerArgs = listOf(
            "-Xexpect-actual-classes",
        )
    }
}
