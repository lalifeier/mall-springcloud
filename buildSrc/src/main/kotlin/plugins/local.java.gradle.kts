plugins {
  java
  `java-library`
//  id("org.springframework.boot")
//  id("io.spring.dependency-management")
}

tasks.withType<JavaCompile> {
  sourceCompatibility = JavaVersion.VERSION_17.toString()
  targetCompatibility = JavaVersion.VERSION_17.toString()
  options.encoding = "UTF-8"
}

tasks.withType<Test> {
  useJUnitPlatform()
}
