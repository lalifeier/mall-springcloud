dependencies {
  implementation(project(":mall-cloud-starter:mall-cloud-spring-cloud-starter"))
  implementation(project(":mall-cloud-starter:mall-cloud-mybatis-plus-spring-boot-starter"))
  implementation(project(":mall-cloud-starter:mall-cloud-prometheus-spring-boot-starter"))
  implementation(project(":mall-cloud-business:mall-cloud-authorization:mall-cloud-authorization-api"))
  implementation("org.springframework.boot:spring-boot-starter-validation")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  implementation(libs.commons.lang3)
  implementation(libs.mysql.connector.java)
  implementation(libs.druid.spring.boot.starter)
  implementation(libs.mapstruct)
  annotationProcessor(libs.mapstruct.processor)
}
