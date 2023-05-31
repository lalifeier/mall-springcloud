dependencies {
  api(project(":mall-cloud-common:mall-cloud-common-core"))
  api(project(":mall-cloud-starter:mall-cloud-logging-spring-boot-starter"))
  api("org.springframework.boot:spring-boot-starter-web") {
    exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
  }
  api("org.springframework.boot:spring-boot-starter-undertow")
  testApi("org.springframework.boot:spring-boot-starter-test")
  api("org.springframework.boot:spring-boot-starter-actuator")
  api("io.micrometer:micrometer-registry-prometheus")
  implementation("org.springframework.boot:spring-boot-starter-aop")
  implementation("org.springframework.boot:spring-boot-configuration-processor")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.springframework.boot:spring-boot-starter-cache")
  implementation("com.github.ben-manes.caffeine:caffeine")
  implementation("org.apache.skywalking:apm-toolkit-logback-1.x")
  implementation("org.apache.skywalking:apm-toolkit-trace")
  implementation("com.google.guava:guava")
  implementation("com.google.code.gson:gson")
  implementation("org.apache.commons:commons-lang3")
}
