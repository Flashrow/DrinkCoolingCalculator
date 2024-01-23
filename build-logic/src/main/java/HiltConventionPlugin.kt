import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import pl.flashrow.buildlogic.utils.implementation
import pl.flashrow.buildlogic.utils.kapt

class HiltConventionPlugin : Plugin<Project>{
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("com.google.dagger.hilt.android")
            }

            extensions.configure<KaptExtension> {
                correctErrorTypes = true
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                implementation(libs.findLibrary("hilt-android").get())
                kapt(libs.findLibrary("hilt-android-compiler").get())
            }
        }
    }
}