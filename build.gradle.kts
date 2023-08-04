plugins {
    `version-catalog`
    `maven-publish`
}

/* Use this code to change the version catalog file location. By default it refers from gradle/libs.versions.toml
catalog {
    versionCatalog {
        from(files("gradle/libs.versions.toml"))

    }
}
*/

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.simonchius.android"
            artifactId = "version.catalog"
            version = "1.0.2"
            from(components["versionCatalog"])
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/simonchius/Centralized-Version-Catalog")
            credentials {
                credentials {
                    username = System.getenv("USER_GITHUB_ID") ?: System.getProperty("USER_GITHUB_ID")
                    password = System.getenv("USER_GITHUB_ACCESS_TOKEN") ?: System.getProperty("USER_GITHUB_ACCESS_TOKEN")
                }
            }
        }
    }
}
