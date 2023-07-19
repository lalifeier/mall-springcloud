plugins {
  `kotlin-dsl`
//  `java-gradle-plugin`
//  id("org.jetbrains.kotlin.jvm")

//  alias(libs.plugins.spring.boot)
//  alias(libs.plugins.spring.dependency.management) apply false
//  alias(libs.plugins.kotlin.jvm) apply false
//  alias(libs.plugins.kotlin.spring) apply false
//  alias(libs.plugins.docker.remote.api)
//  alias(libs.plugins.docker.spring.boot.application)
//  alias(libs.plugins.protobuf)
//  alias(libs.plugins.spotless)
//  alias(libs.plugins.sonarqube)
//  alias(libs.plugins.versions)
}

dependencies {
//  implementation("org.jetbrains.kotlin:kotlin-stdlib-

//  implementation("org.springframework.boot:spring-boot-gradle-plugin:3.1.1")
//  implementation("io.spring.gradle:dependency-management-plugin:1.1.2")
//
//  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
//  implementation("org.jetbrains.kotlin:kotlin-allopen:1.9.0")
//  implementation("org.jetbrains.kotlin:kotlin-noarg:1.9.0")
//
//  implementation("com.google.protobuf:protobuf-gradle-plugin:0.9.4")
  implementation("com.bmuschko:gradle-docker-plugin:9.3.1")
//  implementation("com.github.ben-manes:gradle-versions-plugin:0.47.0")
//  implementation("com.diffplug.spotless:spotless-plugin-gradle:6.20.0")
}

//tasks.register<JavaCompile>("compileKotlin") {
//  sourceCompatibility = JavaVersion.VERSION_1_8
//  targetCompatibility = JavaVersion.VERSION_1_8
//  options.encoding = "UTF-8"
//}
