plugins {
    `kotlin-dsl`
}

group = "me.flashrow.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.gradle)
    compileOnly(libs.kotlin.gradle.plugin)
    implementation(kotlin("script-runtime"))
}
gradlePlugin {
    plugins {
        register("applicationPlugin") {
            id = "me.flashrow.application"
            implementationClass = "ApplicationConventionPlugin"
        }
        register("composePlugin") {
            id = "me.flashrow.application.compose"
            implementationClass = "ComposeConventionPlugin"
        }
        register("hiltComposePlugin") {
            id = "me.flashrow.hilt.compose"
            implementationClass = "HiltComposeConventionPlugin"
        }
        register("hiltPlugin") {
            id = "me.flashrow.hilt"
            implementationClass = "HiltConventionPlugin"
        }
        register("libraryComposePlugin") {
            id = "me.flashrow.library.compose"
            implementationClass = "LibraryComposeConventionPlugin"
        }
        register("libraryPlugin") {
            id = "me.flashrow.library"
            implementationClass = "LibraryConventionPlugin"
        }
    }
}