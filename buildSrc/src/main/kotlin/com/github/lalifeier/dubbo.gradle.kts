package com.github.lalifeier

//val osName = System.getProperty("os.name").toLowerCase()
//val osArch = System.getProperty("os.arch").toLowerCase()
//val osClassifier = when {
//  osName.contains("windows") -> "windows-x86_64"
//  osName.contains("mac") -> "osx-x86_64"
//  osName.contains("linux") -> if (osArch.contains("64")) "linux-x86_64" else "linux-x86"
//  else -> throw GradleException("Unsupported OS: ${osName.toUpperCase()}")
//}


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
