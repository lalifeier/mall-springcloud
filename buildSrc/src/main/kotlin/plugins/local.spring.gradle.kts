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
