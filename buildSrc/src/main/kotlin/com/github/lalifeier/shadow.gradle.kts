package com.github.lalifeier

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.kotlin.dsl.withType

plugins {
  id("com.github.johnrengelman.shadow")
}

tasks.withType<ShadowJar> {
  isZip64 = true
  mergeServiceFiles()
//  archiveClassifier.set("")
}


