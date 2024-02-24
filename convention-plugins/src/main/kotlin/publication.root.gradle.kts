plugins {
    id("io.github.gradle-nexus.publish-plugin")
}

allprojects {
    group = "io.github.pseudoankit"
    version = (System.getenv("RELEASE_TAG_NAME") ?: "1.7.1-SNAPSHOT").replace("v", "")
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
            stagingProfileId.set(System.getenv("OSS_STAGING_PROFILE_ID"))
            username.set(System.getenv("OSS_USERNAME"))
            password.set(System.getenv("OSS_PASSWORD"))
        }
    }
}
