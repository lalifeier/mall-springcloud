dependencies {
  api(project(":mall-cloud-common:mall-cloud-common-core"))
  implementation("org.springframework.boot:spring-boot-configuration-processor")
  api(libs.mybatis.plus.boot.starter)
  implementation(libs.commons.lang3)
  implementation(libs.commons.collections4)
}
