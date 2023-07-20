import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
  idea
  java
  `java-library`

  alias(libs.plugins.spring.boot) apply false
  alias(libs.plugins.spring.dependency.management) apply false
  alias(libs.plugins.kotlin.jvm) apply false
  alias(libs.plugins.kotlin.spring) apply false
//  alias(libs.plugins.docker.remote.api)
//  alias(libs.plugins.docker.spring.boot.application)
//  alias(libs.plugins.protobuf)
//  alias(libs.plugins.spotless)
//  alias(libs.plugins.sonarqube)
//  alias(libs.plugins.versions)

  protobuf apply false
  maven apply false
  spotless apply false
  versions apply false
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
  apply(plugin = "idea")
  apply(plugin = "org.springframework.boot")
  apply(plugin = "io.spring.dependency-management")
  apply(plugin = "org.jetbrains.kotlin.jvm")
  apply(plugin = "org.jetbrains.kotlin.plugin.spring")
  apply(plugin = "maven")

  configurations {
    all {
      resolutionStrategy {
        cacheChangingModulesFor(24, "hours")
        cacheDynamicVersionsFor(24, "hours")
      }
    }
    getByName("compileOnly") {
      extendsFrom(getByName("annotationProcessor"))
    }
  }

  tasks.named<BootJar>("bootJar") {
    enabled = false
  }

  tasks.withType<JavaCompile> {
    sourceCompatibility = JavaVersion.VERSION_17.toString()
    targetCompatibility = JavaVersion.VERSION_17.toString()
    options.encoding = "UTF-8"
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      jvmTarget = JavaVersion.VERSION_17.toString()
      freeCompilerArgs = listOf("-Xjsr305=strict")
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

  tasks.withType<Test> {
    useJUnitPlatform()
  }
}

//grpc
configure(grpcProjects) {
  apply(plugin = "protobuf")
}

//val osName = System.getProperty("os.name").toLowerCase()
//val osArch = System.getProperty("os.arch").toLowerCase()
//val osClassifier = when {
//  osName.contains("windows") -> "windows-x86_64"
//  osName.contains("mac") -> "osx-x86_64"
//  osName.contains("linux") -> if (osArch.contains("64")) "linux-x86_64" else "linux-x86"
//  else -> throw GradleException("Unsupported OS: ${osName.toUpperCase()}")
//}

//dubbo
//configure(dubboProjects) {
////  apply(from = "$rootDir/gradle/protobuf.gradle.kts")
//
//  apply(plugin = "com.google.protobuf")
//
//  protobuf {
//    protoc {
//      artifact = "com.google.protobuf:protoc:${Versions.protobuf}"
////        "com.google.protobuf:protoc:${Versions.protobuf}:${org.gradle.internal.os.OperatingSystem.current()}"
////      artifact = "com.google.protobuf:protoc:${Versions.protobuf}:exe:${osClassifier}"
//    }
//
//    plugins {
//      create("dubbo") {
//        artifact = "org.apache.dubbo:dubbo-compiler:${Versions.dubbo}"
////        mainClass = "org.apache.dubbo.gen.tri.Dubbo3TripleGenerator"
//      }
//    }
//    generateProtoTasks {
//      all().forEach {
//        it.plugins {
//          create("dubbo")
//        }
//      }
//    }
//  }
//}

//docker
//configure(bootProjects) {
////  apply(plugin = "org.springframework.boot")
//  apply(plugin = "com.bmuschko.docker-remote-api")
//  apply(plugin = "com.bmuschko.docker-spring-boot-application")
//
//
////  apply(from =  rootProject.file("gradle/docker.gradle.kts"))
//
//  tasks.named<BootJar>("bootJar") {
//    enabled = true
//  }
//
//  tasks.named<Jar>("jar") {
//    from(sourceSets.main.get().output)
//  }
//
////  val developmentOnly by configurations.creating
////  configurations {
////    runtimeClasspath {
////      extendsFrom(developmentOnly)
////    }
////  }
//
//  dependencies {
//    developmentOnly("org.springframework.boot:spring-boot-devtools")
//  }
//
//  docker {
//    registryCredentials {
//      url.set(project.getVariable("DOCKER_REGISTRY_URL"))
//      username.set(project.getVariable("DOCKER_USERNAME"))
//      password.set(project.getVariable("DOCKER_PASSWORD"))
//    }
//
//    springBootApplication {
//      baseImage.set("openjdk:17-alpine")
////    maintainer.set("lalifeier")
//      ports.set(listOf(9090, 8080))
//      images.set(
//        setOf(
//          "${project.group}/${project.name}:${project.version}",
//          "${project.group}/${project.name}:latest"
//        )
//      )
//      jvmArgs.set(listOf("-Dspring.profiles.active=prod"))
//    }
//  }
//}



