dependencies {
  implementation(project(":mall-cloud-starter:mall-cloud-spring-cloud-starter"))
  implementation(project(":mall-cloud-starter:mall-cloud-mybatis-plus-spring-boot-starter"))
  implementation(project(":mall-cloud-business:mall-cloud-account:mall-cloud-account-api"))
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation(libs.commons.lang3)
  implementation(libs.mysql.connector.java)
  implementation(libs.druid.spring.boot.starter)
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  implementation(libs.mapstruct)
  annotationProcessor(libs.mapstruct.processor)
//  implementation(projects)
}
