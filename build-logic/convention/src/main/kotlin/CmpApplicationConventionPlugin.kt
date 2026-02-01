import com.plcoding.chirp.convention.configureAndroidTarget
import com.plcoding.chirp.convention.configureIosTarget
import com.plcoding.chirp.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class CmpApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(
                    plugin = libs.findPlugin("kotlin-multiplatform").get().get().pluginId
                )
                apply(
                    plugin = libs.findPlugin("convention-android-application-compose").get()
                        .get().pluginId
                )
                apply(plugin = libs.findPlugin("compose-compiler").get().get().pluginId)
                apply(plugin = libs.findPlugin("compose-multiplatform").get().get().pluginId)
            }

            configureAndroidTarget()
            configureIosTarget()

            dependencies {
                "debugImplementation"(libs.findLibrary("androidx-compose-ui-tooling").get())
            }
        }
    }
}