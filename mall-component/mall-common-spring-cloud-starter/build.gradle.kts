dependencies {
  api(project(":mall-component:mall-common-spring-boot-starter"))
  api("org.springframework.cloud:spring-cloud-starter-bootstrap")
  api("org.springframework.cloud:spring-cloud-starter-loadbalancer")
  api("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
  api("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config")
  implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel")
  implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
}
