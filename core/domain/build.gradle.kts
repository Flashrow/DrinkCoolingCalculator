plugins {
    id("me.flashrow.library")
    id("me.flashrow.hilt")
}

android {
    namespace = "pl.flashrow.dcc.core.domain"
}

dependencies {
    implementation(rootProject.project(":core:model"))
    implementation(rootProject.project(":core:data"))
    implementation(rootProject.project(":core:resources"))
}