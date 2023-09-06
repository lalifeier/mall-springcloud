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

    // importOrder()
    // removeUnusedImports()
    //  trimTrailingWhitespace()

    // fix formatting of type annotations
    // formatAnnotations()

    // Choose one of these formatters.
    // eclipse().configFile("$rootDir/style/eclipse-java-google-style.xml")
    palantirJavaFormat("2.35.0")

    // licenseHeaderFile("$rootDir/style/spotless.license.java", "(package|import)")
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
