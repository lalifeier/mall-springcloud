rootProject.name = "buildSrc"
//
//pluginManagement {
//  plugins {
//    id("org.springframework.boot") version Versions.springBootVersion
//    kotlin("jvm") version Versions.kotlinVersion
//    kotlin("plugin.spring") version Versions.kotlinVersion
//    id("com.bmuschko.gradle-docker-plugin") version Versions.gradleDockerPluginVersion
//    id("com.google.protobuf") version Versions.protobufVersion
//  }
//
//  repositories {
//    mavenLocal()
//    maven("https://maven.aliyun.com/repository/public/")
//    maven("https://developer.huawei.com/repo/")
//    mavenCentral()
//    gradlePluginPortal()
//  }
//
////  resolutionStrategy {
////    eachPlugin {
////      if (requested.id.id == "org.springframework.boot") {
////        useModule("org.springframework.boot:spring-boot-gradle-plugin:${requested.version}")
////      }
////      if (requested.id.namespace == "com.google.protobuf") {
////        useModule("com.google.protobuf:protobuf-gradle-plugin:${requested.version}")
////      }
////    }
////  }
//}
//
//dependencyResolutionManagement {
////  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
//  repositories {
//    mavenLocal()
//    maven("https://maven.aliyun.com/repository/public/")
//    maven("https://developer.huawei.com/repo/")
//    mavenCentral()
//  }
//}
