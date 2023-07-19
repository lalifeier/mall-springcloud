rootProject.name = "buildSrc"

pluginManagement {
  plugins {}

  repositories {
    mavenLocal()
    maven("https://developer.huawei.com/repo/")
    maven("https://maven.aliyun.com/repository/public/")
    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
//  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    mavenLocal()
    maven("https://developer.huawei.com/repo/")
    maven("https://maven.aliyun.com/repository/public/")
    mavenCentral()
  }

//  enableFeaturePreview("VERSION_CATALOGS")
  versionCatalogs {
    create("libs") {
      from(files("../gradle/libs.versions.toml"))
    }
  }
}
