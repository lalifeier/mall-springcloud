dependencies {
  api(project(":mall-cloud-common:mall-cloud-common-core"))
  api(project(":mall-cloud-common:mall-cloud-common-web"))
  api(project(":mall-cloud-starter:mall-cloud-log-spring-boot-starter"))
  api(project(":mall-cloud-starter:mall-cloud-trace-spring-boot-starter"))
  api("org.springframework.boot:spring-boot-starter-web") {
    exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
  }
  api("org.springframework.boot:spring-boot-starter-undertow")
  testApi("org.springframework.boot:spring-boot-starter-test")
  implementation("org.springframework.boot:spring-boot-starter-aop")
  implementation("org.springframework.boot:spring-boot-configuration-processor")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.springframework.boot:spring-boot-starter-cache")
  implementation(libs.caffeine)
  implementation(libs.guava)
  implementation(libs.gson)
  implementation(libs.commons.lang3)
}
