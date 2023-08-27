import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
  id("org.springframework.boot")
  id("io.spring.dependency-management")
}


tasks.named<Jar>("jar") {
  enabled = true
  archiveClassifier.set("")
}

tasks.named<BootJar>("bootJar") {
  enabled = false
}


//tasks.register<Copy>("copyJar") {
//  from("${buildDir}/libs")
//  into(dockerBuild)
//  dependsOn("bootJar")
//}
//
//tasks.register<Delete>("clearJar") {
//}
