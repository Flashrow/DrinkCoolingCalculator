package pl.flashrow.buildlogic.utils

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

fun Project.configureCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    commonExtension.apply {
        buildFeatures.compose = true
        composeOptions.kotlinCompilerExtensionVersion =
            libs.findVersion("androidxComposeCompiler").get().toString()
        with(pluginManager){
            apply("org.jetbrains.kotlin.plugin.compose")
            apply("com.google.devtools.ksp")
        }
    }

    dependencies {
        add("implementation", libs.findLibrary("compose-bom").get())
        add("implementation", libs.findLibrary("androidx-compose-foundation").get())
        add("implementation", libs.findLibrary("androidx-compose-foundation-layout").get())
        add("implementation", libs.findLibrary("androidx-compose-material-icons-extended").get())
        add("implementation", libs.findLibrary("androidx-compose-material3").get())
        add("implementation", libs.findLibrary("androidx-compose-runtime").get())
        add("implementation", libs.findLibrary("androidx-compose-runtime-livedata").get())
        add("implementation", libs.findLibrary("androidx-compose-ui").get())
        add("implementation", libs.findLibrary("androidx-compose-ui-util").get())
        add("implementation", libs.findLibrary("material3").get())
        add("implementation", libs.findLibrary("androidx-compose-material3-windowSizeClass").get())
        add("implementation", libs.findLibrary("androidx-compose-material3").get())
        add("implementation", libs.findLibrary("androidx-compose-destinations-animations").get())
        add("ksp", libs.findLibrary("androidx-compose-destinations-ksp").get())
        add("implementation", libs.findLibrary("androidx-compose-destinations-codegen").get())
        add("implementation", libs.findLibrary("androidx-compose-destinations-core").get())
        add("implementation", libs.findLibrary("lottie-compose").get())
        add("implementation", libs.findLibrary("play-services-ads").get())
        add("implementation", libs.findLibrary("androidx-lifecycle-process").get())

        add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
        add("debugImplementation", libs.findLibrary("androidx-compose-ui-test-manifest").get())
    }
}