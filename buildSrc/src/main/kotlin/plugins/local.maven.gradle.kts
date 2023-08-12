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
      url = uri(project.getVariable("MAVEN_REPOSITORY_URL") as String)
      credentials {
        username = project.getVariable("MAVEN_REPOSITORY_USERNAME")
        password = project.getVariable("MAVEN_REPOSITORY_PASSWORD")
      }
    }
  }
}
