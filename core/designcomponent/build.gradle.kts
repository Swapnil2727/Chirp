plugins {
    alias(libs.plugins.convention.cmp.library)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                // Add KMP dependencies here

                implementation(projects.core.presentation)

                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
            }
        }
        androidMain {
            dependencies { }
        }
        iosMain {
            dependencies { }
        }
    }
}