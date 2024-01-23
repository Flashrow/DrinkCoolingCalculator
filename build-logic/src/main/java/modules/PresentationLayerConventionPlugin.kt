package modules

import HiltComposeConventionPlugin
import LibraryComposeConventionPlugin
import LibraryConventionPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import HiltConventionPlugin
import pl.flashrow.buildlogic.utils.implementation

class PresentationLayerConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(LibraryConventionPlugin::class.java)
                apply(LibraryComposeConventionPlugin::class.java)
                apply(HiltConventionPlugin::class.java)
                apply(HiltComposeConventionPlugin::class.java)
            }
            dependencies {
                implementation(rootProject.project("core:presentation"))
                implementation(rootProject.project("core:ui"))
            }
        }
    }
}