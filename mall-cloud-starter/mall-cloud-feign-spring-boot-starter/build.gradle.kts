dependencies {
  api(project(":mall-cloud-common:mall-cloud-common-core"))
  api("org.springframework.cloud:spring-cloud-starter-openfeign")
  api("io.github.openfeign:feign-okhttp")
  implementation("com.google.code.gson:gson")
  implementation("org.springframework.boot:spring-boot-autoconfigure")
  implementation("org.springframework.boot:spring-boot-configuration-processor")
//  implementation("org.springframework.retry:spring-retry")
//  implementation("org.aspectj:aspectjweaver")
}
