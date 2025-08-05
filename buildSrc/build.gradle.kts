plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(androidxLibs.gradle)
    implementation(kotlinLibs.gradle)
    implementation(libs.ktlint)
    implementation(gradleApi())
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}

gradlePlugin {
    plugins {
        register("shortcutHelper") {
            id = "com.github.zellius.shortcut-helper"
            implementationClass = "ru.solodovnikov.shortcuthelper.ShortcutHelperPlugin"
        }
    }
}
