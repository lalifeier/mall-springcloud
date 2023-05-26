object Plugins {
  const val springBoot = "org.springframework.boot:spring-boot-gradle-plugin:${Versions.springBoot}"
  const val gradleDocker = "com.bmuschko:gradle-docker-plugin:${Versions.gradleDockerPlugin}"
  const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
  const val kotlinAllopen = "org.jetbrains.kotlin:kotlin-allopen:${Versions.kotlin}"
  // ASSUMES GRADLE 2.12 OR HIGHER. Use plugin version 0.7.5 with earlier
  // gradle versions
  const val protobufGradle = "com.google.protobuf:protobuf-gradle-plugin:${Versions.protobufGradlePlugin}"
}
