dependencies {
  implementation("org.springframework.boot:spring-boot-starter-aop")
  implementation(libs.logstash.logback.encoder)
  implementation(libs.apm.toolkit.logback)
  implementation(libs.apm.toolkit.trace)
}
