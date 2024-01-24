package modules

import LibraryConventionPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import HiltConventionPlugin
import pl.flashrow.buildlogic.utils.implementation

class DataLayerConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
             with(pluginManager) {
                 apply(LibraryConventionPlugin::class.java)
                 apply(HiltConventionPlugin::class.java)
             }
            dependencies {
//                implementation(rootProject.project("core:data"))
            }
        }
    }
}