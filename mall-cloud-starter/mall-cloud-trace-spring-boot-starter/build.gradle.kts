dependencies {
  api(project(":mall-cloud-common:mall-cloud-common-core"))
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-autoconfigure")
  implementation("org.springframework.boot:spring-boot-configuration-processor")
  implementation(libs.slf4j.api)
  implementation(libs.feign.core)
  implementation(libs.dubbo)
  implementation(libs.transmittable.thread.local)
  implementation(libs.commons.lang3)
}
