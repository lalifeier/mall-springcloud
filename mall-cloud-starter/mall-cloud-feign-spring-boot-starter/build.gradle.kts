dependencies {
  api(project(":mall-cloud-common:mall-cloud-common-core"))
  api("org.springframework.cloud:spring-cloud-starter-openfeign")
  implementation("org.springframework.boot:spring-boot-autoconfigure")
  implementation("org.springframework.boot:spring-boot-configuration-processor")
  api(libs.feign.okhttp)
  implementation(libs.gson)
//  implementation(libs.spring.retry)
//  implementation(libs.aspectjweaver)
}
