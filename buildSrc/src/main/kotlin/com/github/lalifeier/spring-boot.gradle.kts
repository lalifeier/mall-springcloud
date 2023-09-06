package com.github.lalifeier

import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
  java
  id("org.springframework.boot")
  id("io.spring.dependency-management")
}

tasks.named<Jar>("jar") {
  enabled = false
}

tasks.named<BootJar>("bootJar") {
  enabled = true
}

dependencies {
  runtimeOnly("org.springframework.boot:spring-boot-devtools")
//    runtimeOnly("org.springframework.boot:spring-boot-properties-migrator")
}

//tasks.register<Copy>("copyJar") {
//  from("${buildDir}/libs")
//  into(dockerBuild)
//  dependsOn("bootJar")
//}
//
//tasks.register<Delete>("clearJar") {
//}
