dependencies {
  implementation(project(":mall-cloud-starter:mall-cloud-spring-cloud-starter"))
  implementation(project(":mall-cloud-starter:mall-cloud-jpa-spring-boot-starter"))
  implementation("mysql:mysql-connector-java")
  implementation("com.alibaba:druid-spring-boot-starter")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.security:spring-security-oauth2-authorization-server")
  implementation("org.springframework.boot:spring-boot-starter-data-redis")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  implementation("org.mapstruct:mapstruct")
  implementation("org.apache.commons:commons-lang3")
}
