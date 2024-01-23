import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import pl.flashrow.buildlogic.utils.configureAndroid

class LibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
             with(pluginManager) {
                 apply("com.android.library")
                 apply("org.jetbrains.kotlin.android")
                 apply("org.jetbrains.kotlin.kapt")
                 apply("com.google.devtools.ksp")
             }

            extensions.configure<LibraryExtension> {
                configureAndroid(this)
                defaultConfig.apply {
                    targetSdk = 34
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFile("consumer-rules.pro")
                    buildTypes.getByName("debug") {
                        enableUnitTestCoverage = true
                    }
                }
            }
        }
    }
}