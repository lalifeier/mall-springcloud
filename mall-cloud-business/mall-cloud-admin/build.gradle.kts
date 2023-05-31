dependencies {
  implementation(project(":mall-cloud-starter:mall-cloud-spring-cloud-starter"))
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("mysql:mysql-connector-java")
  implementation("com.alibaba:druid-spring-boot-starter")
  implementation("com.alibaba.cloud:spring-cloud-starter-stream-rocketmq")
  implementation("cn.hutool:hutool-all")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
}
