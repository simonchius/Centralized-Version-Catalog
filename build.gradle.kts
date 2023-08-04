plugins {
    `version-catalog`
    `maven-publish`
}

catalog {
    versionCatalog {
        from(files("centralized.version.catalog.toml"))

    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.simonchius.android"
            artifactId = "version.catalog"
            version = "1.1.0"
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
