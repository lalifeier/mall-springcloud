dependencies {
  api(project(":mall-cloud-common:mall-cloud-common-core"))
  api(project(":mall-cloud-common:mall-cloud-common-web"))
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-data-redis")
  implementation("org.springframework.boot:spring-boot-starter-aop")
  implementation("org.springframework.boot:spring-boot-autoconfigure")
  implementation("org.springframework.boot:spring-boot-configuration-processor")
  implementation("org.apache.commons:commons-lang3")
}
