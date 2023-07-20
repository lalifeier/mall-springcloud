dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-configuration-processor")
  implementation(libs.commons.lang3)
  implementation(libs.commons.collections4)
  annotationProcessor(libs.annotation.api)
  annotationProcessor(libs.jackson.annotations)
  annotationProcessor(libs.jackson.databind)
}
