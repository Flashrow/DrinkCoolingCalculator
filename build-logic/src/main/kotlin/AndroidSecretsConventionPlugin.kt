import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidSecretsConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")

            extensions.configure<ApplicationExtension> {
                secrets {
                    // Optional: Configure properties for the plugin if needed
                    // defaultPropertiesFileName = "secrets.properties"
                }
            }
        }
    }
}
