package com.github.lalifeier

plugins {
  id("com.diffplug.spotless")
}

spotless {
  format("misc") {
    target("**/*.gradle", "**/.md", "**/.gitignore")
    trimTrailingWhitespace()
    indentWithTabs()
    endWithNewline()
  }

  java {
    target("src/*/java/**/*.java")
    targetExclude("**/build/")

    importOrder()

    removeUnusedImports()

    eclipse().configFile("$rootDir/style/eclipse-java-google-style.xml")

    formatAnnotations()

//    licenseHeaderFile("$rootDir/style/spotless.license.java", "(package|import)")
  }

  kotlin {
    target("**/*.kt")
    targetExclude("**/build/")
    ktlint()
  }

  kotlinGradle {
    target("**/*.gradle.kts")
    targetExclude("**/build/")
    ktlint()
  }
}
