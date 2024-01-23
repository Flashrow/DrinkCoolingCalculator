package pl.flashrow.buildlogic.utils

import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.implementation(dependency: Any) {
    add("implementation", dependency)
}

fun DependencyHandlerScope.debugImplementation(dependency: Any) {
    add("debugImplementation", dependency)
}

fun DependencyHandlerScope.kapt(dependency: Any) {
    add("kapt", dependency)
}

fun DependencyHandlerScope.api(dependency: Any) {
    add("api", dependency)
}