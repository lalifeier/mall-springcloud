import com.bmuschko.gradle.docker.tasks.image.Dockerfile


plugins {
  id("com.bmuschko.docker-remote-api")
  id("com.bmuschko.docker-spring-boot-application")
}

docker {
  registryCredentials {
    url.set(project.getVariable("DOCKER_REGISTRY_URL"))
    username.set(project.getVariable("DOCKER_USERNAME"))
    password.set(project.getVariable("DOCKER_PASSWORD"))
  }

  springBootApplication {
//    baseImage.set("openjdk:17-alpine")
////    maintainer.set("lalifeier")
//    ports.set(listOf(9090, 8080))
    images.set(setOf("${project.group}/${project.name}:${project.version}", "${project.group}/${project.name}:latest"))
//    jvmArgs.set(listOf("-Dspring.profiles.active=prod"))
  }
}

tasks.named<Dockerfile>("dockerCreateDockerfile") {
  instructions.set(emptyList())
  instructionsFromTemplate(file("$rootDir/deploy/Dockerfile"))
//  instructionsFromTemplate(file("$projectDir/src/docker/Dockerfile"))
  destFile.set(file("build/docker/Dockerfile"))
}
