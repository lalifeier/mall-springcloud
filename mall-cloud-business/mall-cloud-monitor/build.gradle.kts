dependencies {
  implementation(project(":mall-cloud-starter:mall-cloud-spring-cloud-starter"))
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation(libs.spring.boot.admin.starter.server)
  implementation(libs.spring.boot.starter.actuator)
  implementation(libs.micrometer.registry.prometheus)
}
