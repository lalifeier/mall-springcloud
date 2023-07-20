dependencies {
  implementation(project(":mall-cloud-starter:mall-cloud-spring-cloud-starter"))
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("com.alibaba.cloud:spring-cloud-starter-stream-rocketmq")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  implementation(libs.mysql.connector.java)
  implementation(libs.druid.spring.boot.starter)
  implementation(libs.hutool)
}
