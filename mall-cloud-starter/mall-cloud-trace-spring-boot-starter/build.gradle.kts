dependencies {
  api(project(":mall-cloud-common:mall-cloud-common-core"))
  implementation("org.slf4j:slf4j-api")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("io.github.openfeign:feign-core")
  implementation("org.apache.dubbo:dubbo")
  implementation("com.alibaba:transmittable-thread-local")
  implementation("org.springframework.boot:spring-boot-autoconfigure")
  implementation("org.springframework.boot:spring-boot-configuration-processor")
}
