dependencies {
  implementation(project(":mall-component:mall-common-spring-boot-starter"))
  implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
  implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
  implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("mysql:mysql-connector-java")
  implementation("com.alibaba:druid-spring-boot-starter")
  implementation("com.alibaba.cloud:spring-cloud-starter-stream-rocketmq")
  implementation("cn.hutool:hutool-all")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
}
