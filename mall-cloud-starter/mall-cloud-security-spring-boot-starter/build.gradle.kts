dependencies {
  implementation(project(":mall-cloud-common:mall-cloud-common-core"))
  api("org.springframework.boot:spring-boot-starter-security")
  implementation("com.fasterxml.jackson.core:jackson-databind")
  implementation(libs.servlet.api)
}
