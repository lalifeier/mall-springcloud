dependencies {
  api(project(":mall-cloud-common:mall-cloud-common-core"))
//  implementation("org.springframework.boot:spring-boot-starter-web")
//  implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
//  implementation("org.apache.dubbo:dubbo-spring-boot-starter")
  implementation("org.slf4:slf4j-api")
  implementation("org.springframework.boot:spring-web")
  implementation("io.github.openfeign:feign-core")
  implementation("org.apache.dubbo:dubbo")
  implementation("com.alibaba:transmittable-thread-local")
//  implementation("javax.servlet:javax.servlet-api:4.0.1")
  implementation("org.springframework.boot:spring-boot-starter:3.1.1")
  implementation("org.springframework.boot:spring-boot-configuration-processor")
}
