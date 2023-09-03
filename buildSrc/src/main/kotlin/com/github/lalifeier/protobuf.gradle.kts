package com.github.lalifeier

//import com.github.lalifeier.internal.libs

plugins {
  id("com.google.protobuf")
}

//val protocVersion = libs.findVersion("protobuf").get()
//val protocKotlinVersion = libs.findVersion("grpc-kotlin").get()
//val grpcVersion = libs.findVersion("grpc").get()

val protocVersion = "3.22.3"
val protocKotlinVersion = "1.3.0"
val grpcVersion = "1.57.1"

protobuf {
  protoc {
    artifact = "com.google.protobuf:protoc:$protocVersion"
  }

  plugins {
    create("grpc") {
      artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
    }
//    create("grpckt") {
//      artifact = "io.grpc:protoc-gen-grpc-kotlin:$protocKotlinVersion:jdk8@jar"
//    }
  }

  generateProtoTasks {
    all().forEach {
      it.plugins {
        create("grpc") {
          option("lite")
        }
//          create("grpckt") {
//            option("lite")
//          }
      }
      it.builtins {
        named("java") {
          option("lite")
        }
//          create("kotlin") {
//            option("lite")
//          }
      }
    }
  }
}
