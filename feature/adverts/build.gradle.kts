import java.io.FileInputStream
import java.util.Properties

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
localProperties.load(FileInputStream(localPropertiesFile))
val appOpenAdvertId: String = localProperties.getProperty("APP_OPEN_AD_ID")

plugins {
    id("me.flashrow.feature")
    id("me.flashrow.library")
    id("me.flashrow.library.compose")
    id("me.flashrow.hilt")
}

android {
    namespace = "pl.flashrow.dcc.feature.adverts"
    defaultConfig{
        buildConfigField("String", "APP_OPEN_AD_ID", "\"$appOpenAdvertId\"")
    }
}

dependencies {
    implementation(rootProject.project(":core:common"))
    implementation(rootProject.project(":core:resources"))
}