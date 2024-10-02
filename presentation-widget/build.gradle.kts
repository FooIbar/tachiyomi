import org.jetbrains.kotlin.compose.compiler.gradle.ComposeFeatureFlag

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("plugin.compose")
}

android {
    namespace = "tachiyomi.presentation.widget"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":presentation-core"))
    api(project(":i18n"))

    implementation(compose.glance)
    lintChecks(compose.lintchecks)

    implementation(kotlinx.immutables)

    implementation(platform(libs.coil.bom))
    implementation(libs.coil.core)

    api(libs.injekt.core)
}

composeCompiler {
    if (project.findProperty("tachiyomi.enableComposeCompilerMetrics") == "true") {
        val composeMetrics = project.layout.buildDirectory.dir("compose_metrics").get()
        reportsDestination = composeMetrics
        metricsDestination = composeMetrics
    }

    featureFlags = setOf(ComposeFeatureFlag.OptimizeNonSkippingGroups)
}
