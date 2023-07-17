dependencies {
  implementation(project(":mall-cloud-starter:mall-cloud-spring-cloud-starter"))
  implementation(project(":mall-cloud-starter:mall-cloud-mybatis-plus-spring-boot-starter"))
  implementation(project(":mall-cloud-business:mall-cloud-account:mall-cloud-account-api"))
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.apache.commons:commons-lang3")
  implementation("mysql:mysql-connector-java")
  implementation("com.alibaba:druid-spring-boot-starter")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  implementation("org.mapstruct:mapstruct")
  annotationProcessor("org.mapstruct:mapstruct-processor")
}
