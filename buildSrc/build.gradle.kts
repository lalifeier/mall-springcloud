plugins {
  `kotlin-dsl`
//  `java-gradle-plugin`
//  id("org.jetbrains.kotlin.jvm")

//  alias(libs.plugins.spring.boot)
//  alias(libs.plugins.spring.dependency.management) apply false
//  alias(libs.plugins.kotlin.jvm) apply false
//  alias(libs.plugins.kotlin.spring) apply false

//  alias(libs.plugins.protobuf)
//  alias(libs.plugins.spotless)
//  alias(libs.plugins.sonarqube)
//  alias(libs.plugins.versions)
}

dependencies {
//  implementation(libs.spring.boot.gradle.plugin)
//  implementation(libs.spring.dependency.management.plugin)

//  implementation(libs.kotlin.gradle.plugin)
//  implementation(libs.kotlin.allopen)
//  implementation(libs.kotlin.noarg)

  implementation(libs.protobuf.gradle.plugin)
//  implementation(libs.docker.gradle.plugin)
  implementation(libs.spotless.gradle.plugin)
//  implementation(libs.sonarqube.gradle.plugin)
  implementation(libs.gradle.versions.plugin)
}

//tasks.register<JavaCompile>("compileKotlin") {
//  sourceCompatibility = JavaVersion.VERSION_1_8
//  targetCompatibility = JavaVersion.VERSION_1_8
//  options.encoding = "UTF-8"
//}
