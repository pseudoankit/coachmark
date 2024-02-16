plugins {
    alias(libs.plugins.android.app) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.gradle.nexus.publish)
}

nexusPublishing {
    repositories {
        sonatype {
            username = System.getenv("OSS_USERNAME")
            password = System.getenv("OSS_PASSWORD")
            stagingProfileId = System.getenv("OSS_STAGING_PROFILE_ID")

            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}