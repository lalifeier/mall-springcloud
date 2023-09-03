package com.github.lalifeier

import com.github.lalifeier.internal.getVariable
import gradle.kotlin.dsl.accessors._f94fc6c8215be330ed97c169cddc9497.sonarqube

plugins {
  id("org.sonarqube")
}

sonarqube {
  properties {
    getVariable("SONAR_HOST")?.let { property("sonar.host.url", it) }
  }
}
