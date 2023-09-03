package com.github.lalifeier

import org.gradle.kotlin.dsl.java
import org.gradle.kotlin.dsl.`java-library`

plugins {
  java
  `java-library`
}

tasks.withType<JavaCompile> {
  sourceCompatibility = JavaVersion.VERSION_17.toString()
  targetCompatibility = JavaVersion.VERSION_17.toString()
  options.encoding = "UTF-8"
}

tasks.withType<Test> {
  useJUnitPlatform()
}


