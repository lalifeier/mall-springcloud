dependencies {
  implementation(project(":mall-component:mall-common-spring-boot-starter"))
  implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
  implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
  implementation("de.codecentric:spring-boot-admin-starter-server")
  implementation("org.springframework.boot:spring-boot-starter-security")
}
