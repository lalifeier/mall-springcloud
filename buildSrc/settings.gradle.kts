rootProject.name = "buildSrc"

pluginManagement {
  plugins {
    kotlin("jvm") version Versions.kotlinVersion
  }

  repositories {
    mavenLocal()
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
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://developer.huawei.com/repo/")
    mavenCentral()
  }
}
