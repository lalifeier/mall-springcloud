dependencies {
  implementation(project(":mall-common"))
  implementation(project(":mall-component:mall-logging-spring-boot-starter"))
  implementation(project(":mall-component:mall-common-spring-cloud-starter")) {
    exclude(group = "com.github.lalifeier", module = "mall-common-spring-boot-starter")
  }
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("io.micrometer:micrometer-registry-prometheus")
  implementation("org.springframework.cloud:spring-cloud-starter-gateway")
  implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
  implementation("org.springframework.boot:spring-boot-starter-data-redis")
  implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
  implementation("commons-beanutils:commons-beanutils")
  implementation("cn.hutool:hutool-all")
  implementation("org.springframework.boot:spring-boot-configuration-processor")
  implementation("org.apache.commons:commons-lang3")
  implementation("com.google.code.gson:gson")
}
