import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

tasks.withType<KotlinCompile>().configureEach {

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.gradle.publish)
}
