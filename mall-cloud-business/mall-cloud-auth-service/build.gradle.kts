dependencies {
  implementation(project(":mall-cloud-starter:mall-cloud-spring-cloud-starter"))
  implementation(project(":mall-cloud-starter:mall-cloud-jpa-spring-boot-starter"))
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.boot:spring-boot-starter-data-redis")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  implementation(libs.mysql.connector.java)
  implementation(libs.druid.spring.boot.starter)
  implementation(libs.spring.security.oauth2.authorization.server)
  implementation(libs.mapstruct)
  implementation(libs.commons.lang3)
}
