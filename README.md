# Centralized Version Catalog for Android
An Android version catalog is a file that contains a list of dependencies and their versions. It is used to manage dependencies and plugins in a scalable way. Android version catalogs are supported by Gradle, the build system for Android. <br /> <br />
A centralized version catalog is having the version catalog set up in a remote repository and consuming it in a project and dependent modules. <br />

# How to publish a centralized version catalog 
<br />
1. Add a TOML file under the root directory <br />
For Eg: I created a file named "centralized.version.catalog.toml" under the root directory with sample dependencies  <br />

```

[versions]
agp = "8.2.0-alpha09"
kotlin = "1.8.21"
core-ktx = "1.9.0"
junit = "4.13.2"
androidx-test-ext-junit = "1.1.5"
espresso-core = "3.5.1"
appcompat = "1.6.1"
material = "1.9.0"
constraintlayout = "2.1.4"
navigation-fragment-ktx = "2.6.0"
navigation-ui-ktx = "2.6.0"

[libraries]
core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "core-ktx" }
junit = { group = "junit", name = "junit", version.ref = "junit" }
androidx-test-ext-junit = { group = "androidx.test.ext", name = "junit", version.ref = "androidx-test-ext-junit" }
espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espresso-core" }
appcompat = { group = "androidx.appcompat", name = "appcompat", version.ref = "appcompat" }
material = { group = "com.google.android.material", name = "material", version.ref = "material" }
constraintlayout = { group = "androidx.constraintlayout", name = "constraintlayout", version.ref = "constraintlayout" }
navigation-fragment-ktx = { group = "androidx.navigation", name = "navigation-fragment-ktx", version.ref = "navigation-fragment-ktx" }
navigation-ui-ktx = { group = "androidx.navigation", name = "navigation-ui-ktx", version.ref = "navigation-ui-ktx" }

[plugins]
androidApplication = { id = "com.android.application", version.ref = "agp" }
kotlinAndroid = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }

[bundles]
```

2. Configure the centralized.version.catalog.toml in build.gradle.kts 
```
catalog {
    versionCatalog {
        from(files("centralized.version.catalog.toml"))

    }
}
```
3. Finally publish it to maven <br />
 In my example, I have published it to GitHub packages. by adding the following code in build.gradle.kts
```
publishing {
    publications {
        // Do not forget to change the groupId, artifactId and version
        create<MavenPublication>("maven") {
            groupId = "com.simonchius.android" 
            artifactId = "version.catalog"
            version = "1.1.0"
            from(components["versionCatalog"])
        }
    }
    repositories {
        maven {
            // Do not forget to change the repository url and credentials.
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
```
