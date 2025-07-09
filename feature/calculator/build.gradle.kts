import java.io.FileInputStream
import java.util.Properties

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
localProperties.load(FileInputStream(localPropertiesFile))
val resultBannerAdId: String = localProperties.getProperty("RESULT_BANNER_AD_ID")

plugins {
    id("me.flashrow.feature")
    id("me.flashrow.library")
    id("me.flashrow.library.compose")
    id("me.flashrow.hilt")
}

android {
    namespace = "pl.flashrow.dcc.feature.calculator"
    defaultConfig{
        buildConfigField("String", "RESULT_BANNER_AD_ID", "\"$resultBannerAdId\"")
    }
}

dependencies {
    implementation(rootProject.project(":core:common"))
    implementation(rootProject.project(":core:resources"))
}