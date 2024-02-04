plugins {
    id("me.flashrow.library")
    id("me.flashrow.hilt")
}

android {
    namespace = "pl.flashrow.dcc.core.data"
}

dependencies {
    implementation(rootProject.project(":core:resources"))
}