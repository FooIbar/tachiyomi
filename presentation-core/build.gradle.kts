plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("plugin.compose")
}

android {
    namespace = "tachiyomi.presentation.core"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    api(project(":core"))
    api(project(":i18n"))

    // Compose
    implementation(platform(compose.bom))
    implementation(compose.activity)
    implementation(compose.foundation)
    implementation(compose.material3.core)
    implementation(compose.material.icons)
    implementation(compose.animation)
    implementation(compose.animation.graphics)
    debugImplementation(compose.ui.tooling)
    implementation(compose.ui.tooling.preview)
    implementation(compose.ui.util)
    lintChecks(compose.lintchecks)

    implementation(kotlinx.immutables)
}

composeCompiler {
    if (project.findProperty("tachiyomi.enableComposeCompilerMetrics") == "true") {
        val composeMetrics = project.layout.buildDirectory.dir("compose_metrics").get()
        reportsDestination = composeMetrics
        metricsDestination = composeMetrics
    }

    enableNonSkippingGroupOptimization = true

    // https://medium.com/androiddevelopers/jetpack-compose-strong-skipping-mode-explained-cbdb2aa4b900
    enableStrongSkippingMode = true
}

tasks {
    // See https://kotlinlang.org/docs/reference/experimental.html#experimental-status-of-experimental-api(-markers)
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions.freeCompilerArgs = listOf(
            "-opt-in=androidx.compose.foundation.layout.ExperimentalLayoutApi",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
            "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
            "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
            "-opt-in=androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi",
            "-opt-in=kotlinx.coroutines.FlowPreview",
        )
    }
}
