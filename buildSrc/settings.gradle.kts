rootProject.name = "buildSrc"

pluginManagement {
  plugins {
//    id("org.springframework.boot") version Versions.springBoot
//    id("io.spring.dependency-management") version Versions.kotlin
//    id("org.jetbrains.kotlin.jvm") version Versions.kotlin
//    id("org.jetbrains.kotlin.plugin.spring") version Versions.kotlinVersion
//    id("com.bmuschko.docker-remote-api") version Versions.gradleDockerPlugin
//    id("com.google.protobuf") version Versions.protobuf

//    id("org.springframework.boot") version "2.6.13"
//    id("io.spring.dependency-management") version "1.1.0"
//    id("org.jetbrains.kotlin.jvm") version "1.8.21"
//    id("org.jetbrains.kotlin.plugin.spring") version "1.8.21"
//    id("com.bmuschko.docker-remote-api") version "9.3.1"
//    id("com.google.protobuf") version "0.9.3"
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

//  versionCatalogs {
//    create("libs") {
//      from(files("../gradle/libs.versions.toml"))
//    }
//  }
}
