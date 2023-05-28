import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

//buildscript {
//  repositories {
//    mavenLocal()
//    maven("https://maven.aliyun.com/repository/public/")
//    maven("https://developer.huawei.com/repo/")
//    mavenCentral()
//    gradlePluginPortal()
//  }
//
//  dependencies {
//    classpath(Plugins.springBoot)
//    classpath(Plugins.gradleDocker)
//    classpath(Plugins.kotlinGradle)
//    classpath(Plugins.kotlinAllopen)
//    classpath(Plugins.protobufGradle)
//  }
//}




plugins {
  java
  `java-library`
  idea
  `maven-publish`
  id("org.springframework.boot") version Versions.springBoot
//    https://docs.spring.io/spring-boot/docs/2.6.13/gradle-plugin/reference/htmlsingle/#appendix-dependency-versions
  id("io.spring.dependency-management") version "1.0.15.RELEASE"
  id("org.jetbrains.kotlin.jvm") version  Versions.kotlin
  id("org.jetbrains.kotlin.plugin.spring") version  Versions.kotlin
  id("com.bmuschko.docker-remote-api") version  Versions.dockerGadle
  id("com.bmuschko.docker-spring-boot-application") version  Versions.dockerGadle
  id("com.google.protobuf") version  Versions.protobufGradle
  id("org.sonarqube") version Versions.sonarqube
}

fun getVariable(name: String): String? {
  val value = System.getenv(name)
  if (value != null) {
    return value
  }
  val propName = name.replace('_', '.').toLowerCase()
  return project.findProperty(propName)?.toString()
}

allprojects {
  group = "com.github.lalifeier"
  version = project.findProperty("version") as String

//  sourceSets {
//    main {
//      java.srcDirs("src/main/java")
////      kotlin.srcDirs("src/main/kotlin")
//      proto.srcDirs("src/main/proto")
//      resources.srcDirs("src/main/resources")
//    }
//    test {
//      java.srcDirs("src/test/java")
////      kotlin.srcDirs("src/test/kotlin")
//      proto.srcDirs("src/test/proto")
//      resources.srcDirs("src/test/resources")
//    }
//  }
}

val javaProjects = subprojects.filter { it.file("build.gradle.kts").exists() }
val bootProjects = subprojects.filter { it.name.endsWith("-service") || it.name in listOf("mall-gateway", "mall-monitor", "mall-admin") }
val grpcProjects = subprojects.filter { it.name.endsWith("-grpc") }

//val kotlinProjects = subprojects.filter { it.file("build.gradle.kts").exists() }

configure(javaProjects) {
  apply(plugin = "java")
  apply(plugin = "java-library")
  apply(plugin = "idea")
  apply(plugin = "org.springframework.boot")
  apply(plugin = "io.spring.dependency-management")
  apply(plugin = "org.jetbrains.kotlin.jvm")
  apply(plugin = "org.jetbrains.kotlin.plugin.spring")

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
    dependencies {
      Dependencies.all.forEach { dependency ->
        dependency(dependency)
      }
    }
  }

  dependencies {
    implementation(platform(Dependencies.springBoot))
    implementation(platform(Dependencies.springCloud))
    implementation(platform(Dependencies.springCloudAlibaba))

    compileOnly(Dependencies.lombok)
    annotationProcessor(Dependencies.lombok)
    testCompileOnly(Dependencies.lombok)
    testAnnotationProcessor(Dependencies.lombok)
  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }
}

//grpc
configure(grpcProjects) {
  apply(plugin = "com.google.protobuf")

  protobuf {
    protoc {
      artifact = "com.google.protobuf:protoc:${Versions.protobuf}"
    }

    plugins {
      create("grpc") {
        artifact = "io.grpc:protoc-gen-grpc-java:${Versions.grpc}"
      }
    }
    generateProtoTasks {
      all().forEach {
        it.plugins {
          create("grpc")
        }
      }
    }
  }
}

//maven
configure(javaProjects) {
  apply(plugin = "java-library")
  apply(plugin = "maven-publish")

  publishing {
    publications {
      create<MavenPublication>("mavenJava") {
        from(components["java"])
      }
    }

    repositories {
      maven {
        url = uri(getVariable("MAVEN_REPOSITORY_URL") as String)
        credentials {
          username = getVariable("MAVEN_REPOSITORY_USERNAME")
          password = getVariable("MAVEN_REPOSITORY_PASSWORD")
        }
      }
    }
  }
}

//docker
configure(bootProjects) {
  apply(plugin = "application")
  apply(plugin = "com.bmuschko.docker-remote-api")
  apply(plugin = "com.bmuschko.docker-spring-boot-application")

  tasks.named<BootJar>("bootJar") {
    enabled = true
  }

  tasks.named<Jar>("jar") {
    from(sourceSets.main.get().output)
  }

  dependencies {
    developmentOnly("org.springframework.boot:spring-boot-devtools")
  }

  docker {
    registryCredentials {
      url.set(getVariable("DOCKER_REGISTRY_URL"))
      username.set(getVariable("DOCKER_USERNAME"))
      password.set(getVariable("DOCKER_PASSWORD"))
    }

    springBootApplication {
      baseImage.set("openjdk:8-alpine")
      ports.set(listOf(9090, 8080))
      images.set(setOf("${project.group}/${project.name}:${project.version}", "${project.group}/${project.name}:latest"))
      jvmArgs.set(listOf("-Dspring.profiles.active=prod"))
    }
  }
}



