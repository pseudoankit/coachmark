pluginManagement {
    includeBuild("convention-plugins")
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

//plugins {
//    id("org.gradle.toolchains.foojay-resolver-convention") version "0.4.0"
//}

rootProject.name = "coachmark"

include(":demo:android")
include(":coachmark")
include(":demo:shared")
