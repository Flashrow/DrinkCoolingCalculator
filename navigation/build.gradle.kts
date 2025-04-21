plugins {
    id("me.flashrow.feature")
    id("me.flashrow.library")
    id("me.flashrow.library.compose")
    id("me.flashrow.hilt")
}

android {
    namespace = "pl.flashrow.dcc.navigation"
}

dependencies {
    implementation(rootProject.project(":core:common"))
    implementation(rootProject.project(":feature:calculator"))
}