plugins {
  `kotlin-dsl`
}

dependencies {
  implementation(libs.spring.boot.gradle.plugin)
  implementation(libs.spring.dependency.management.plugin)
  implementation(libs.kotlin.gradle.plugin)
  implementation(libs.kotlin.allopen)
  implementation(libs.kotlin.noarg)
  implementation(libs.protobuf.gradle.plugin)
  implementation(libs.docker.gradle.plugin)
  implementation(libs.spotless.gradle.plugin)
  implementation(libs.sonarqube.gradle.plugin)
  implementation(libs.gradle.versions.plugin)
  implementation(libs.shadow.gradle.plugin)
  implementation(libs.githook.gradle.plugin)
  implementation(libs.commitlint.gradle.plugin)
}
