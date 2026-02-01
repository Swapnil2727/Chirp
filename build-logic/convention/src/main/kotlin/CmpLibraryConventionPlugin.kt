import com.plcoding.chirp.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.Actions.with
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CmpLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(plugin = libs.findPlugin("convention-kmp-library").get().get().pluginId)
                apply(plugin = libs.findPlugin("compose-compiler").get().get().pluginId)
                apply(plugin = libs.findPlugin("compose-multiplatform").get().get().pluginId)
            }

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets {
                    commonMain {
                        dependencies {
                            implementation(libs.findLibrary("jetbrains-compose-ui").get())
                            implementation(libs.findLibrary("jetbrains-compose-foundation").get())
                            implementation(libs.findLibrary("jetbrains-compose-material3").get())
                            implementation(
                                libs.findLibrary("jetbrains-compose-material-icons-core").get()
                            )
                        }
                    }
                }
            }
        }
    }
}
