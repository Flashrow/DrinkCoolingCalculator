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
        }
    }

    dependencies {
        implementation(libs.findLibrary("compose-bom").get())
        implementation(libs.findLibrary("androidx-compose-foundation").get())
        implementation(libs.findLibrary("androidx-compose-foundation-layout").get())
        implementation(libs.findLibrary("androidx-compose-material-icons-extended").get())
        implementation(libs.findLibrary("androidx-compose-material3").get())
        implementation(libs.findLibrary("androidx-compose-runtime").get())
        implementation(libs.findLibrary("androidx-compose-runtime-livedata").get())
        implementation(libs.findLibrary("androidx-compose-ui").get())
        implementation(libs.findLibrary("androidx-compose-ui-util").get())
        implementation(libs.findLibrary("material3").get())
        implementation(libs.findLibrary("androidx-compose-material3-windowSizeClass").get())
        implementation(libs.findLibrary("androidx-compose-material3").get())
        implementation(libs.findLibrary("androidx-compose-destinations-animations").get())
        kapt(libs.findLibrary("androidx-compose-destinations-ksp").get())
        implementation(libs.findLibrary("androidx-compose-destinations-codegen").get())
        implementation(libs.findLibrary("androidx-compose-destinations-core").get())

        debugImplementation(libs.findLibrary("androidx-compose-ui-tooling").get())
        debugImplementation(libs.findLibrary("androidx-compose-ui-test-manifest").get())
    }
}