import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
  idea
  java
  id("local.java") apply false
  id("local.kotlin") apply false
  id("local.spring") apply false
  id("local.protobuf") apply false
  id("local.maven") apply false
  id("local.docker") apply false
  id("local.spotless") apply false
  id("local.sonarqube") apply false
  id("local.versions")
}

allprojects {
  group = "com.github.lalifeier"
  version = project.findProperty("version") as String
}

val javaProjects = subprojects.filter { it.file("build.gradle.kts").exists() }
val bootProjects = subprojects.filter {
  it.name.endsWith("-service") || it.name in listOf(
    "mall-cloud-gateway",
    "mall-cloud-monitor",
    "mall-cloud-admin"
  )
}
val grpcProjects = subprojects.filter { it.name.endsWith("-grpc") }
val dubboProjects = subprojects.filter { it.name.endsWith("-dubbo") }
//val kotlinProjects = subprojects.filter { it.file("build.gradle.kts").exists() }

configure(javaProjects) {
  apply(plugin = "java")
  apply(plugin = "java-library")
  apply(plugin = "local.java")
  apply(plugin = "local.kotlin")
  apply(plugin = "local.spring")
  apply(plugin = "local.maven")

  configurations {
    all {
      resolutionStrategy {
        cacheChangingModulesFor(24, "hours")
        cacheDynamicVersionsFor(24, "hours")
      }
    }
  }

  configure<DependencyManagementExtension> {
    imports {}
    dependencies {}
  }

  dependencies {
    implementation(platform(rootProject.libs.spring.boot.dependencies))
    implementation(platform(rootProject.libs.spring.cloud.dependencies))
    implementation(platform(rootProject.libs.spring.cloud.alibaba.dependencies))

    compileOnly(rootProject.libs.lombok)
    annotationProcessor(rootProject.libs.lombok)
    testCompileOnly(rootProject.libs.lombok)
    testAnnotationProcessor(rootProject.libs.lombok)
  }
}

//grpc
configure(grpcProjects) {
  apply(plugin = "local.protobuf")
}

//dubbo
//configure(dubboProjects) {
//}

//docker
configure(bootProjects) {
  apply(plugin = "local.docker")

  tasks.named<BootJar>("bootJar") {
    enabled = true
  }

  tasks.named<Jar>("jar") {
    from(sourceSets.main.get().output)
  }

//  val developmentOnly1 by configurations.creating
//  configurations {
//    runtimeClasspath {
//      extendsFrom(developmentOnly1)
//    }
//  }

  dependencies {
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.springframework.boot:spring-boot-properties-migrator")
  }
}
