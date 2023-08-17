dependencies {
  implementation(project(":mall-cloud-starter:mall-cloud-spring-cloud-starter"))
  implementation(project(":mall-cloud-starter:mall-cloud-prometheus-spring-boot-starter"))
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation(libs.spring.boot.admin.starter.server)
}
