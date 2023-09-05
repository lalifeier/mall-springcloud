package com.github.lalifeier

plugins {
  java
  `java-library`
}

tasks.withType<JavaCompile> {
  sourceCompatibility = JavaVersion.VERSION_17.toString()
  targetCompatibility = JavaVersion.VERSION_17.toString()
  options.encoding = "UTF-8"
  options.compilerArgs.add("-Xlint:deprecation")
//  options.compilerArgs.add("-Xlint:unchecked")
//  options.compilerArgs.add("-Werror")
}

tasks.withType<Test> {
  useJUnitPlatform()
}


