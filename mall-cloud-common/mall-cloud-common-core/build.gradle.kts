dependencies {
  api(project(":mall-cloud-common:mall-cloud-common-error"))
  implementation("com.google.guava:guava")
  implementation("org.apache.commons:commons-lang3")
  implementation("commons-codec:commons-codec")
  implementation("com.google.code.gson:gson")
  implementation("org.apache.commons:commons-collections4")
  implementation("org.apache.skywalking:apm-toolkit-logback-1.x")
  implementation("org.apache.skywalking:apm-toolkit-trace")

//    implementation("org.springframework.data:spring-data-commons")
//    implementation("com.github.pagehelper:pagehelper")
}
