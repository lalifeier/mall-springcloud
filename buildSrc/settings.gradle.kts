rootProject.name = "buildSrc"

pluginManagement {
  plugins {}

  repositories {
    mavenLocal()
    maven("https://mirrors.tencent.com/nexus/repository/maven-public/")
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://developer.huawei.com/repo/")
    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
//  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    mavenLocal()
    maven("https://mirrors.tencent.com/nexus/repository/maven-public/")
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://developer.huawei.com/repo/")
    mavenCentral()
    gradlePluginPortal()
  }

//  enableFeaturePreview("VERSION_CATALOGS")
  versionCatalogs {
    create("libs") {
      from(files("../gradle/libs.versions.toml"))
    }
  }
}
