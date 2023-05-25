import org.gradle.api.tasks.SourceSetContainer
import com.diffplug.gradle.spotless.KotlinExtension
import com.diffplug.gradle.spotless.SpotlessExtension
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  kotlin("plugin.spring")
  id("java")
  id("org.springframework.boot")
//  id("io.spring.dependency-management")
//  id("com.google.protobuf")
}

//sourceSets {
//  main.java.srcDirs += 'src/main/kotlin'
//}

allprojects {
  apply(plugin = "java")
  apply(plugin = "idea")



  group = "com.github.lalifeier"
  version = Versions.projectVersion

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
//      downloadJavadoc = false
//      downloadSources = false
//      inheritOutputDirs = false
//      outputDir = file("$buildDir/classes/main/")
//      sourceDirs += file("src/generated/main/java")
//      sourceDirs += file("src/generated/main/grpc")
//      generatedSourceDirs += file("src/generated/main/java")
//      generatedSourceDirs += file("src/generated/main/grpc")
//    }
//  }
}


//subprojects {
//  apply(plugin = "java-library")
//  apply(plugin = "maven-publish")
//  apply(plugin = "org.springframework.boot")
//  apply(plugin = "io.spring.dependency-management")
//  apply(plugin = "org.jetbrains.kotlin.jvm")
//  apply(plugin = "org.jetbrains.kotlin.plugin.spring")
//}

val javaProjects = subprojects.filter { it.file("build.gradle").exists() }
val bootProjects = subprojects.filter { it.name.endsWith("-service") || it.name in listOf("mall-gateway", "mall-monitor", "mall-admin") }
val grpcProjects = subprojects.filter { it.name.endsWith("-grpc") }



configure(grpcProjects) {
  apply(plugin = "com.google.protobuf")

//  protobuf {
//    protoc {
//      artifact = "com.google.protobuf:protoc:${Versions.protobufVersion}"
//    }
//    plugins {
//      create("grpc") {
//        artifact = "io.grpc:protoc-gen-grpc-java:${Versions.grpcVersion}"
//      }
//    }
//    generateProtoTasks {
//      all().forEach { task ->
//        task.plugins {
//          create("grpc")
//        }
//      }
//    }
//  }
}


configure(javaProjects) {
  apply(plugin = "java-library")
  apply(plugin = "maven-publish")
  apply(plugin = "org.springframework.boot")
  apply(plugin = "io.spring.dependency-management")
  apply(plugin = "org.jetbrains.kotlin.jvm")
  apply(plugin = "org.jetbrains.kotlin.plugin.spring")

//  configurations {
//    create("compileOnly") {
//      extendsFrom(annotationProcessor)
//    }
//  }
//
//  tasks.bootJar {
//    enabled = false
//  }
//
//  tasks.compileJava {
//    sourceCompatibility = JavaVersion.VERSION_17
//    targetCompatibility = JavaVersion.VERSION_17
//    options.encoding = "UTF-8"
//  }
//
//  [tasks.compileJava, tasks.compileTestJava, tasks.javadoc].forEach {
//    it.options.encoding = "UTF-8"
//  }
//

//  tasks.compileKotlin {
//    kotlinOptions {
//      jvmTarget = JavaVersion.VERSION_17
//      freeCompilerArgs = listOf("-Xjsr305=strict")
//    }
//  }

//  configure<DependencyManagementExtension> {
//    imports {
//      mavenBom("org.springframework.boot:spring-boot-dependencies:${rootProject.ext.springBootVersion}")
//      mavenBom("org.springframework.cloud:spring-cloud-dependencies:${rootProject.ext.springCloudVersion}")
//      mavenBom("com.alibaba.cloud:spring-cloud-alibaba-dependencies:${rootProject.ext.springCloudAlibabaVersion}")
//    }
//
//    dependencies {
//      Dependencies.all.forEach { dependency ->
//        dependency(dependency)
//      }
//    }
//  }

//
//
//  dependencies {
//    // gradle 5.0+
//    implementation(platform("org.springframework.boot:spring-boot-dependencies:${Versions.springBootVersion}"))
//    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:${Versions.springCloudVersion}"))
//    implementation(platform("com.alibaba.cloud:spring-cloud-alibaba-dependencies:${Versions.springCloudAlibabaVersion}"))
//
//    compileOnly("org.projectlombok:lombok:${Versions.lombokVersion}")
//    annotationProcessor("org.projectlombok:lombok:${Versions.lombokVersion}")
//    testCompileOnly("org.projectlombok:lombok:${Versions.lombokVersion}")
//    testAnnotationProcessor("org.projectlombok:lombok:${Versions.lombokVersion}")
//  }

  tasks.withType<Test> {
    useJUnitPlatform()
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
//  tasks.bootJar {
//    enabled = true
//    archiveFileName = "${project.name}.jar"
//  }
//
//  tasks.jar {
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

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}
repositories {
    mavenCentral()
}
kotlin {
    jvmToolchain(11)
}
