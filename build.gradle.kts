import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
  idea
  java
  `java-library`
  alias(libs.plugins.spring.boot) apply false
  alias(libs.plugins.spring.dependency.management) apply false
  id("local.java") apply false
  id("local.kotlin") apply false
  id("local.protobuf") apply false
  id("local.maven") apply false
  id("local.docker") apply false
  id("local.spotless") apply false
  id("local.sonarqube") apply false
  id("local.versions") apply false
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
  apply(plugin = "org.springframework.boot")
  apply(plugin = "io.spring.dependency-management")
  apply(plugin = "local.java")
  apply(plugin = "local.kotlin")
  apply(plugin = "local.maven")

  configurations {
    all {
      resolutionStrategy {
        cacheChangingModulesFor(24, "hours")
        cacheDynamicVersionsFor(24, "hours")
      }
    }
//    getByName("compileOnly") {
//      extendsFrom(getByName("annotationProcessor"))
//    }
  }

  tasks.named<BootJar>("bootJar") {
    enabled = false
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

//    developmentOnly("org.springframework.boot:spring-boot-devtools")
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

//  val developmentOnly by configurations.creating
//  configurations {
//    runtimeClasspath {
//      extendsFrom(developmentOnly)
//    }
//  }

//  dependencies {
//    developmentOnly("org.springframework.boot:spring-boot-devtools")
//  }
}


//sourceSets {
//  main {
//    java {
//      srcDirs("src/main/java")
//      srcDirs("build/generated/source/proto/main/java")
//    }
////    kotlin {
////      srcDir("src/main/kotlin")
////    }
//    proto {
//      srcDirs("src/main/proto")
//    }
//    resources {
//      srcDirs("src/main/resources")
//    }
//  }
//  test {
//    java {
//      srcDirs("src/test/java")
//    }
////    kotlin {
////        srcDirs("src/test/kotlin")
////    }
//    proto {
//      srcDirs("src/test/proto")
//    }
//    resources {
//      srcDirs("src/test/resources")
//    }
//  }
//}
