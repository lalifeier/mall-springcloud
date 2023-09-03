package com.github.lalifeier

import com.github.lalifeier.internal.getVariable

plugins {
  `maven-publish`
}

val isSnapshot = project.version.toString().contains("SNAPSHOT")

publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      from(components["java"])
    }
  }

  repositories {
    maven {
      url = uri(getVariable("MAVEN_REPOSITORY_URL") as String)
      credentials {
        username = getVariable("MAVEN_REPOSITORY_USERNAME")
        password = getVariable("MAVEN_REPOSITORY_PASSWORD")
      }
    }
  }
}
