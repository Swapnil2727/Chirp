import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.plcoding.convention.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.tools.common)
    implementation(libs.buildkonfig.gradlePlugin)
    implementation(libs.buildkonfig.compiler)
}


java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = libs.plugins.convention.android.application.asProvider().get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidComposeApplication") {
            id = libs.plugins.convention.android.application.compose.get().pluginId
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("cmpApplication") {
            id = libs.plugins.convention.cmp.application.get().pluginId
            implementationClass = "CmpApplicationConventionPlugin"
        }
        register("kmpLibrary") {
            id = libs.plugins.convention.kmp.library.get().pluginId
            implementationClass = "KmpLibraryConventionPlugin"
        }
        register("cmpFeature") {
            id = libs.plugins.convention.cmp.feature.get().pluginId
            implementationClass = "CmpFeatureConventionPlugin"
        }
        register("cmpLibrary") {
            id = libs.plugins.convention.cmp.library.get().pluginId
            implementationClass = "CmpLibraryConventionPlugin"
        }

        register("buildKonfig"){
            id = libs.plugins.convention.buildkonfig.get().pluginId
            implementationClass = "BuildKonfigConventionPlugin"
        }

    }
}