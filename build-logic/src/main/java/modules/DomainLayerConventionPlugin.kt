package modules

import LibraryConventionPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import HiltConventionPlugin
import pl.flashrow.buildlogic.utils.api

class DomainLayerConventionPlugin : Plugin<Project>{
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager){
                apply(LibraryConventionPlugin::class.java)
                apply(HiltConventionPlugin::class.java)
            }
            dependencies {
                api(rootProject.project("core:domain"))
            }
        }
    }
}