rootProject.name = "mall-springcloud"

pluginManagement {
//  plugins {
//    id("org.springframework.boot") version "{${Versions.springBoot}}"
//    kotlin("jvm") version "{${Versions.kotlin}}"
//    kotlin("plugin.spring") version  "{${Versions.kotlin}}"
//    id("com.bmuschko.gradle-docker-plugin") version  "{${Versions.gradleDockerPlugin}}"
//    id("com.google.protobuf") version  "{${Versions.protobuf}}"


//    id("org.springframework.boot") version "2.6.13"
//    kotlin("jvm") version "1.8.21"
//    kotlin("plugin.spring") version "1.8.21"
//    id("com.bmuschko.gradle-docker-plugin") version "6.7.0"
//    id("com.google.protobuf") version "3.23.0"
//  }

  repositories {
    mavenLocal()
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://developer.huawei.com/repo/")
    mavenCentral()
    gradlePluginPortal()
  }

//  resolutionStrategy {
//    eachPlugin {
//      if (requested.id.id == "org.springframework.boot") {
//        useModule("org.springframework.boot:spring-boot-gradle-plugin:${requested.version}")
//      }
//      if (requested.id.namespace == "com.google.protobuf") {
//        useModule("com.google.protobuf:protobuf-gradle-plugin:${requested.version}")
//      }
//    }
//  }
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

//mall-common
include("mall-common")

//mall-component
include("mall-component:mall-common-error")
include("mall-component:mall-common-spring-boot-starter")
include("mall-component:mall-common-spring-cloud-starter")
include("mall-component:mall-jpa-spring-boot-starter")
include("mall-component:mall-mybatis-plus-spring-boot-starter")
include("mall-component:mall-swagger-spring-boot-starter")
include("mall-component:mall-redis-spring-boot-starter")
include("mall-component:mall-feign-spring-boot-starter")
include("mall-component:mall-logging-spring-boot-starter")
include("mall-component:mall-sign-spring-boot-starter")
include("mall-component:mall-double-cache-spring-boot-starter")
include("mall-component:mall-http-spring-boot-starter")
include("mall-component:mall-common-version")
include("mall-component:mall-xxl-job-spring-boot-starter")
include("mall-component:mall-local-cache-spring-boot-starter")


//demo
include("mall-demo:demo-api")
include("mall-demo:demo-feign")
include("mall-demo:demo-grpc")
include("mall-demo:demo-dubbo")
include("mall-demo:demo-service")

//gateway
include("mall-gateway")

//monitor
include("mall-monitor")

//admin
include("mall-admin")

//account
include("account-biz:account-api")
include("account-biz:account-grpc")
include("account-biz:account-service")

//auth
include("auth-service")


include("mall-kotlin-demo")
