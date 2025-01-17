import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import pl.flashrow.buildlogic.utils.configureCompose
import pl.flashrow.buildlogic.utils.implementation

class ComposeConventionPlugin : Plugin<Project>{
    override fun apply(target: Project) {
        with(target){
            extensions.configure<BaseAppModuleExtension> {
                configureCompose(commonExtension = this)
            }
            with(pluginManager){
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                implementation(libs.findLibrary("androidx-compose-activity").get())
            }
        }
    }
}