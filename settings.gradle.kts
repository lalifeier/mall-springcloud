rootProject.name = "mall-springcloud"



pluginManagement {
  repositories {
    mavenLocal()
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://developer.huawei.com/repo/")
    mavenCentral()
    gradlePluginPortal()
  }

//  val protobufPlugin = "0.8.17"
//
//  plugins {
//    id("com.google.protobuf") version protobufPlugin
//  }
}

dependencyResolutionManagement {
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
include("mall-cloud-gateway")

//monitor
include("mall-cloud-monitor")

//admin
include("mall-admin")

//account
include("account-biz:account-api")
include("account-biz:account-grpc")
include("account-biz:account-service")

//auth
include("auth-service")


include("mall-cloud-kotlin:mall-cloud-demo")

//bigdata
include("mall-cloud-bigdata:mall-cloud-data-analysis")
include("mall-cloud-bigdata:mall-cloud-data-sync")
