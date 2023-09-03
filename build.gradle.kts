import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
  idea
  java
  id("com.github.lalifeier.java") apply false
  id("com.github.lalifeier.kotlin") apply false
  id("com.github.lalifeier.spring") apply false
  id("com.github.lalifeier.protobuf") apply false
  id("com.github.lalifeier.maven") apply false
  id("com.github.lalifeier.docker") apply false
  id("com.github.lalifeier.sonarqube") apply false
  id("com.github.lalifeier.spotless") apply false
  id("com.github.lalifeier.shadow") apply false
  id("com.github.lalifeier.versions")
  id("com.github.lalifeier.git-hooks")
}

fun isReleaseBuild(): Boolean {
  return hasProperty("release")
}

val version = project.findProperty("version") as String

allprojects {
  group = "com.github.lalifeier"
  version = if (isReleaseBuild()) version else "$version-SNAPSHOT"
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
  apply(plugin = "com.github.lalifeier.java")
  apply(plugin = "com.github.lalifeier.kotlin")
  apply(plugin = "com.github.lalifeier.spring")
  apply(plugin = "com.github.lalifeier.maven")
  apply(plugin = "com.github.lalifeier.spotless")

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
  apply(plugin = "com.github.lalifeier.protobuf")
}

//dubbo
//configure(dubboProjects) {
//}

//docker
configure(bootProjects) {
  apply(plugin = "com.github.lalifeier.docker")
  apply(plugin = "com.github.lalifeier.shadow")

  tasks.named<Jar>("jar") {
    enabled = false
  }

  tasks.named<BootJar>("bootJar") {
    enabled = true
  }

//  val developmentOnly1 by configurations.creating
//  configurations {
//    runtimeClasspath {
//      extendsFrom(developmentOnly1)
//    }
//  }

  dependencies {
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
//    runtimeOnly("org.springframework.boot:spring-boot-properties-migrator")
  }
}



