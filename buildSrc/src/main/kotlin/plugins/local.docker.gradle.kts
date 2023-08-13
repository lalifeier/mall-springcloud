//import com.bmuschko.gradle.docker.tasks.image.Dockerfile
//import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
  id("com.bmuschko.docker-remote-api")
  id("com.bmuschko.docker-spring-boot-application")
//  id("com.github.johnrengelman.shadow")
}

docker {
  registryCredentials {
    url.set(project.getVariable("DOCKER_REGISTRY_URL"))
    username.set(project.getVariable("DOCKER_USERNAME"))
    password.set(project.getVariable("DOCKER_PASSWORD"))
  }

  springBootApplication {
    baseImage.set("apache/skywalking-java-agent:8.16.0-java17")
    maintainer.set("lalifeier")
    ports.set(listOf(9090, 8080))
    images.set(setOf("${project.group}/${project.name}:${project.version}", "${project.group}/${project.name}:latest"))
//    jvmArgs.set(listOf("-Dspring.profiles.active=prod"))
  }
}

//val bootJar = tasks.getByName<BootJar>("bootJar")
//val shadowJar = tasks.getByName<ShadowJar>("shadowJar")

//val dockerSyncJar = tasks.register<Copy>("dockerSyncJar") {
//  dependsOn(bootJar)
//  from(bootJar.archiveFile)
//  into("$buildDir/docker")
//  rename { _ ->
//    "app.jar"
//  }
//}
//
//tasks.named<Dockerfile>("dockerCreateDockerfile") {
//  instructions.set(emptyList())
//  destFile.set(file("build/docker/Dockerfile"))
//  from("apache/skywalking-java-agent:8.16.0-java17")
//  workingDir("/app")
//  copyFile("app.jar", "/app/app.jar")
//  environmentVariable("JAVA_OPTS", "\"\"")
//  environmentVariable("SW_AGENT_NAME", "demo")
//  environmentVariable("SW_AGENT_COLLECTOR_BACKEND_SERVICES", "localhost:11800")
//  environmentVariable("NACOS_SERVER_ADDR", "localhost:8848")
//  environmentVariable("NACOS_NAMESPACE", "\"\"")
//  environmentVariable(
//    "AGENT_OPTS",
//    "-Dskywalking.agent.service_name=\$SW_AGENT_NAME -Dskywalking.collector.backend_service=\$SW_AGENT_COLLECTOR_BACKEND_SERVICES -DNACOS_SERVER_ADDR=\$NACOS_SERVER_ADDR -DNACOS_NAMESPACE=\$NACOS_NAMESPACE"
//  )
//
//  entryPoint("sh", "-c")
//  defaultCommand("java \$JAVA_OPTS -javaagent:/skywalking/agent/skywalking-agent.jar \$AGENT_OPTS -jar /app/app.jar")
//
//  dependsOn(dockerSyncJar)
//}

//tasks.named<Dockerfile>("dockerCreateDockerfile") {
//  instructions.set(emptyList())
//  instructionsFromTemplate(file("$rootDir/deploy/Dockerfile"))
////  instructionsFromTemplate(file("$projectDir/src/docker/Dockerfile"))
//  destFile.set(file("build/docker/Dockerfile"))
//}
