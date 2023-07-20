dependencies {
  implementation(project(":mall-cloud-starter:mall-cloud-spring-boot-starter"))
  implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
  implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation(libs.spring.boot.admin.starter.server)
}
