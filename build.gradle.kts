buildscript {
  apply(from = "version.gradle.kts")

  repositories {
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://developer.huawei.com/repo/")
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
  }

  dependencies {
    classpath("org.springframework.boot:spring-boot-gradle-plugin:${Versions.springBootVersion}")
    classpath("com.bmuschko:gradle-docker-plugin:${Versions.gradleDockerPluginVersion}")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}")
    classpath("org.jetbrains.kotlin:kotlin-allopen:${Versions.kotlinVersion}")
    // ASSUMES GRADLE 2.12 OR HIGHER. Use plugin version 0.7.5 with earlier
    // gradle versions
    classpath("com.google.protobuf:protobuf-gradle-plugin:${Versions.protobufGradlePluginVersion}")
  }
}

allprojects {
  group = "com.github.lalifeier"
  version = rootProject.ext.projectVersion

  apply(plugin = "idea")

  idea {
    module {
      downloadJavadoc = false
      downloadSources = false
      inheritOutputDirs = false
      outputDir = file("$buildDir/classes/main/")
      sourceDirs += file("src/generated/main/java")
      sourceDirs += file("src/generated/main/grpc")
      generatedSourceDirs += file("src/generated/main/java")
      generatedSourceDirs += file("src/generated/main/grpc")
    }
  }
}

run {
  javaProjects = subprojects.filter { it.file("build.gradle").exists() }
  bootProjects = subprojects.filter { it.name.endsWith("-service") || it.name in listOf("mall-gateway", "mall-monitor", "mall-admin") }
  grpcProjects = subprojects.filter { it.name.endsWith("-grpc") }
}

configure(grpcProjects) { project ->
  apply(plugin = "com.google.protobuf")

  protobuf {
    protoc {
      artifact = "com.google.protobuf:protoc:${Versions.protobufVersion}"
    }
    plugins {
      create("grpc") {
        artifact = "io.grpc:protoc-gen-grpc-java:${Versions.grpcVersion}"
      }
    }
    generateProtoTasks {
      all().forEach { task ->
        task.plugins {
          create("grpc")
        }
      }
    }
  }
}


configure(javaProjects) { project ->
  apply(plugin = "java-library")
  apply(plugin = "maven-publish")
  apply(plugin = "org.springframework.boot")
  apply(plugin = "io.spring.dependency-management")
  apply(plugin = "org.jetbrains.kotlin.jvm")
  apply(plugin = "org.jetbrains.kotlin.plugin.spring")

  repositories {
    maven(url = "https://maven.aliyun.com/repository/public/")
    maven(url = "https://developer.huawei.com/repo/")
    mavenLocal()
    mavenCentral()
  }

  configurations {
    create("compileOnly") {
      extendsFrom(annotationProcessor)
    }
  }

  tasks.bootJar {
    enabled = false
  }

  tasks.compileJava {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    options.encoding = "UTF-8"
  }

  [tasks.compileJava, tasks.compileTestJava, tasks.javadoc].forEach {
    it.options.encoding = "UTF-8"
  }

  tasks.compileKotlin {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17
    kotlinOptions.freeCompilerArgs = listOf("-Xjsr305=strict")
  }

  dependencyManagement {
//    imports {
//      mavenBom("org.springframework.boot:spring-boot-dependencies:${rootProject.ext.springBootVersion}")
//      mavenBom("org.springframework.cloud:spring-cloud-dependencies:${rootProject.ext.springCloudVersion}")
//      mavenBom("com.alibaba.cloud:spring-cloud-alibaba-dependencies:${rootProject.ext.springCloudAlibabaVersion}")
//    }


    dependencies {
      Dependencies.all.forEach { dependency ->
        dependency(dependency)
      }
    }
  }


  dependencies {
    // gradle 5.0+
    implementation(platform("org.springframework.boot:spring-boot-dependencies:${Versions.pringBootVersion}"))
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:${Versions.springCloudVersion}"))
    implementation(platform("com.alibaba.cloud:spring-cloud-alibaba-dependencies:${Versions.springCloudAlibabaVersion}"))

    compileOnly("org.projectlombok:lombok:${Versions.lombokVersion}")
    annotationProcessor("org.projectlombok:lombok:${Versions.lombokVersion}")
    testCompileOnly("org.projectlombok:lombok:${Versions.lombokVersion}")
    testAnnotationProcessor("org.projectlombok:lombok:${Versions.lombokVersion}")
  }


  test {
    useJUnitPlatform()
  }


}

//maven
configure(javaProjects) { project ->
  publishing {
    publications {
      create<MavenPublication>("mavenJava") {
        from(components["java"])
      }
    }

    repositories {
      maven {
        url = uri( MAVEN_REPOSITORY_URL )
        credentials {
          username = MAVEN_REPOSITORY_USERNAME
          password = MAVEN_REPOSITORY_PASSWORD
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

  tasks.bootJar {
    enabled = true
    archiveFileName = "${project.name}.jar"
  }

  tasks.jar {
    from(sourceSets.main.get().output)
  }

  dependencies {
    developmentOnly("org.springframework.boot:spring-boot-devtools")
  }

  docker {
    registryCredentials {
      url = DOCKER_REGISTRY_URL
      username = DOCKER_USERNAME
      password = DOCKER_PASSWORD
    }

    springBootApplication {
      baseImage = "openjdk:8-alpine"
      ports = listOf(8080)
      images = listOf("${project.group}/${project.name}:${project.version}", "${project.group}/${project.name}:latest")
      jvmArgs = listOf("-Dspring.profiles.active=prod")
    }
  }
}

