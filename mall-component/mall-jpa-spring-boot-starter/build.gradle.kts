dependencies {
  api(project(":mall-common"))
  api("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("com.querydsl:querydsl-jpa")
  annotationProcessor("com.querydsl:querydsl-apt")
}
