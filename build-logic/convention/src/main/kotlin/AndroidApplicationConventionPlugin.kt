import com.android.build.api.dsl.ApplicationExtension
import com.plcoding.chirp.convention.configureKotlinAndroid
import com.plcoding.chirp.convention.getVersion
import com.plcoding.chirp.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")

            extensions.configure<ApplicationExtension> {
                namespace = "com.plcoding.chirp"
                defaultConfig {
                    applicationId = libs.getVersion("projectApplicationId")
                    targetSdk = libs.getVersion("projectTargetSdkVersion").toInt()
                    versionCode = libs.getVersion("projectVersionCode").toInt()
                    versionName = libs.getVersion("projectVersionName")
                }

                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }

                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                    }
                }
                configureKotlinAndroid(this)
            }
        }
    }
}
