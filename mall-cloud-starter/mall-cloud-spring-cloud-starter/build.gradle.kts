dependencies {
  api(project(":mall-cloud-starter:mall-cloud-spring-boot-starter"))
  api("org.springframework.cloud:spring-cloud-starter-bootstrap")
  api("org.springframework.cloud:spring-cloud-starter-loadbalancer")
  api("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery") {
    exclude(group = "org.springframework.cloud", module = "spring-cloud-starter-netflix-ribbon")
  }
  api("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config")
  implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel")
  implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
}
