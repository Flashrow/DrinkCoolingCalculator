import org.apache.groovy.json.internal.Chr.add
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import pl.flashrow.buildlogic.utils.implementation

class FeatureConventionPlugin : Plugin<Project>{
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager){
                apply(LibraryConventionPlugin::class.java)
                apply(LibraryComposeConventionPlugin::class.java)
                apply(HiltConventionPlugin::class.java)
                apply(HiltComposeConventionPlugin::class.java)
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                implementation(rootProject.project("core:ui"))

                implementation(libs.findLibrary("hilt-navigation").get())
                implementation(libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
                implementation(libs.findLibrary("androidx-activity-compose").get())
            }
        }
    }
}