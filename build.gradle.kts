import org.gradle.api.tasks.SourceSetContainer
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar
//import org.gradle.plugins.ide.*

buildscript {
  repositories {
    mavenLocal()
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://developer.huawei.com/repo/")
    mavenCentral()
    gradlePluginPortal()
  }

  dependencies {
    classpath(Plugins.springBoot)
    classpath(Plugins.gradleDocker)
    classpath(Plugins.kotlinGradle)
    classpath(Plugins.kotlinAllopen)
    classpath(Plugins.protobufGradle)
  }
}

//plugins {
//  kotlin("jvm")
//  kotlin("plugin.spring")
//  id("org.springframework.boot")
////  id("io.spring.dependency-management")
////  id("com.google.protobuf")
//}

allprojects {
  apply(plugin = "idea")

  group = "com.github.lalifeier"
  version = Versions.project

//  configure<SourceSetContainer> {
//    sourceSets {
//      main {
//        java {
//          srcDir("thirdParty/src/main/java")
//        }
//      }
//    }
//  }

//  idea {
//    module {
//      downloadJavadoc.set(false)
//      downloadSources.set(false)
//      inheritOutputDirs.set(false)
//      outputDir = file("$buildDir/classes/main/")
//      sourceDirs += file("src/generated/main/java")
//      sourceDirs += file("src/generated/main/grpc")
//      generatedSourceDirs += file("src/generated/main/java")
//      generatedSourceDirs += file("src/generated/main/grpc")
//    }
//  }
}

val javaProjects = subprojects.filter { it.file("build.gradle").exists() }
val bootProjects = subprojects.filter { it.name.endsWith("-service") || it.name in listOf("mall-gateway", "mall-monitor", "mall-admin") }
val grpcProjects = subprojects.filter { it.name.endsWith("-grpc") }

configure(javaProjects) {
  apply(plugin = "java-library")
  apply(plugin = "maven-publish")
  apply(plugin = "org.springframework.boot")
  apply(plugin = "io.spring.dependency-management")
  apply(plugin = "org.jetbrains.kotlin.jvm")
  apply(plugin = "org.jetbrains.kotlin.plugin.spring")

  configurations {
    getByName("compileOnly") {
      extendsFrom(getByName("annotationProcessor"))
    }
  }

  tasks.named<BootJar>("bootJar") {
    enabled = false
  }

  tasks.withType<JavaCompile> {
    sourceCompatibility = JavaVersion.VERSION_1_8.toString()
    targetCompatibility = JavaVersion.VERSION_1_8.toString()
    options.encoding = "UTF-8"
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      jvmTarget = JavaVersion.VERSION_1_8.toString()
      freeCompilerArgs = listOf("-Xjsr305=strict")
    }
  }

  configure<DependencyManagementExtension> {
    imports {

    }
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

configure(grpcProjects) {
  apply(plugin = "com.google.protobuf")

  protobuf {
    protoc {
      artifact = "com.google.protobuf:protoc:${Versions.protobuf}"
    }

    generateProtoTasks {
      all().configureEach { task ->
        task.builtins {
          java {
            option "lite"
          }
        }
      }
    }
  }
}

//maven
//configure(javaProjects) {
//  publishing {
//    publications {
//      create<MavenPublication>("mavenJava") {
//        from(components["java"])
//      }
//    }
//
//    repositories {
//      maven {
//        url = uri(MAVEN_REPOSITORY_URL)
//        credentials {
//          username = MAVEN_REPOSITORY_USERNAME
//          password = MAVEN_REPOSITORY_PASSWORD
//        }
//      }
//    }
//  }
//}

//docker
//configure(bootProjects) {
//  apply(plugin = "application")
//  apply(plugin = "com.bmuschko.docker-remote-api")
//  apply(plugin = "com.bmuschko.docker-spring-boot-application")
//
//  tasks.named("bootJar") {
//    enabled = true
//    archiveFileName = "${project.name}.jar"
//  }
//
//  tasks.named<Jar>("jar") {
//    from(sourceSets.main.get().output)
//  }
//
//  dependencies {
//    developmentOnly("org.springframework.boot:spring-boot-devtools")
//  }
//
//  docker {
//    registryCredentials {
//      url = DOCKER_REGISTRY_URL
//      username = DOCKER_USERNAME
//      password = DOCKER_PASSWORD
//    }
//
//    springBootApplication {
//      baseImage = "openjdk:8-alpine"
//      ports = listOf(8080)
//      images = listOf("${project.group}/${project.name}:${project.version}", "${project.group}/${project.name}:latest")
//      jvmArgs = listOf("-Dspring.profiles.active=prod")
//    }
//  }
//}



