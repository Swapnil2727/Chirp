import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec
import com.codingfeline.buildkonfig.gradle.BuildKonfigExtension
import com.plcoding.chirp.convention.libs
import com.plcoding.chirp.convention.pathToPackageName
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class BuildKonfigConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(plugin = libs.findPlugin("buildkonfig").get().get().pluginId)
            }

            extensions.configure<BuildKonfigExtension>() {
                packageName = target.pathToPackageName()
                defaultConfigs {
                    val apiKey = gradleLocalProperties(rootDir, rootProject.providers)
                        .getProperty("API_KEY")
                        ?: throw IllegalStateException("Invalid or missing API_KEY in local.properties")
                    buildConfigField(
                        type = FieldSpec.Type.STRING,
                        name = "API_KEY",
                        value = apiKey,
                        const = true
                    )
                }
            }
        }
    }
}