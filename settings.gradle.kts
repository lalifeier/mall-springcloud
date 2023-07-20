rootProject.name = "mall-springcloud"

pluginManagement {
  repositories {
    mavenLocal()
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://developer.huawei.com/repo/")
    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
  repositories {
    mavenLocal()
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://developer.huawei.com/repo/")
    mavenCentral()
    gradlePluginPortal()
  }
}

//mall-cloud-common
include("mall-cloud-common:mall-cloud-common-core")
include("mall-cloud-common:mall-cloud-common-error")
include("mall-cloud-common:mall-cloud-common-version")
include("mall-cloud-common:mall-cloud-common-dict")
include("mall-cloud-common:mall-cloud-common-mark")
include("mall-cloud-common:mall-cloud-common-web")
include("mall-cloud-common:mall-cloud-common-distributed-id")

//mall-cloud-starter
include("mall-cloud-starter:mall-cloud-spring-boot-starter")
include("mall-cloud-starter:mall-cloud-spring-cloud-starter")
include("mall-cloud-starter:mall-cloud-jpa-spring-boot-starter")
include("mall-cloud-starter:mall-cloud-mybatis-plus-spring-boot-starter")
include("mall-cloud-starter:mall-cloud-openapi-spring-boot-starter")
include("mall-cloud-starter:mall-cloud-redis-spring-boot-starter")
include("mall-cloud-starter:mall-cloud-feign-spring-boot-starter")
include("mall-cloud-starter:mall-cloud-log-spring-boot-starter")
include("mall-cloud-starter:mall-cloud-signature-spring-boot-starter")
include("mall-cloud-starter:mall-cloud-double-cache-spring-boot-starter")
include("mall-cloud-starter:mall-cloud-http-spring-boot-starter")
include("mall-cloud-starter:mall-cloud-xxl-job-spring-boot-starter")
include("mall-cloud-starter:mall-cloud-local-cache-spring-boot-starter")
include("mall-cloud-starter:mall-cloud-encrypt-body-spring-boot-starter")
include("mall-cloud-starter:mall-cloud-repeat-submit-spring-boot-starter")
include("mall-cloud-starter:mall-cloud-rate-limiter-spring-boot-starter")
include("mall-cloud-starter:mall-cloud-trace-spring-boot-starter")

//demo
include("mall-cloud-business:mall-cloud-demo:mall-cloud-demo-api")
include("mall-cloud-business:mall-cloud-demo:mall-cloud-demo-feign")
include("mall-cloud-business:mall-cloud-demo:mall-cloud-demo-grpc")
include("mall-cloud-business:mall-cloud-demo:mall-cloud-demo-dubbo")
include("mall-cloud-business:mall-cloud-demo:mall-cloud-demo-service")

//gateway
include("mall-cloud-business:mall-cloud-gateway")

//monitor
include("mall-cloud-business:mall-cloud-monitor")

//admin
include("mall-cloud-business:mall-cloud-admin")

//account
include("mall-cloud-business:mall-cloud-account:mall-cloud-account-api")
include("mall-cloud-business:mall-cloud-account:mall-cloud-account-grpc")
include("mall-cloud-business:mall-cloud-account:mall-cloud-account-service")

//auth
include("mall-cloud-business:mall-cloud-auth-service")


include("mall-cloud-kotlin:mall-cloud-demo")

//bigdata
include("mall-cloud-bigdata:mall-cloud-data-analysis")
include("mall-cloud-bigdata:mall-cloud-data-sync")
