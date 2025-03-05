pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Drink Cooling Calculator"
include(":app")
include(":data")
include(":feature")
include(":test")
include(":core:data")
include(":core:domain")
include(":core:ui")
include(":core:common")
include(":feature:calculator")
include(":core:designsystem")
include(":core:model")
include(":core:resources")
include(":core:common")
include(":navigation")
