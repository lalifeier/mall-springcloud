plugins {
  id("org.sonarqube")
}

sonarqube {
  properties {
    project.getVariable("SONAR_HOST")?.let { property("sonar.host.url", it) }
  }
}
