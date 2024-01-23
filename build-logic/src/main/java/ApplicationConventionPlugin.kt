import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import pl.flashrow.buildlogic.utils.configureAndroid

class ApplicationConventionPlugin :Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("dagger.hilt.android.plugin")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.kapt")
                apply("com.google.devtools.ksp")
            }
            extensions.configure<BaseAppModuleExtension> {
                configureAndroid(commonExtension = this)
            }
        }
    }
}