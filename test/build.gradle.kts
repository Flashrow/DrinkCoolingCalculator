plugins {
    id("me.flashrow.feature")
    id("me.flashrow.library")
    id("me.flashrow.library.compose")
    id("me.flashrow.hilt")
}

android {
    namespace = "pl.flashrow.dcc.test"
}

dependencies {
    implementation(rootProject.project(":core:common"))
}