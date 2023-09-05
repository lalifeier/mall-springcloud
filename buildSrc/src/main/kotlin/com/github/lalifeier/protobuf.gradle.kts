package com.github.lalifeier

plugins {
  id("com.google.protobuf")
}

val protocVersion = "3.22.3"
val grpcVersion = "1.57.2"
val grpcKotlinVersion = "1.3.0"

protobuf {
  protoc {
    artifact = "com.google.protobuf:protoc:$protocVersion"
  }

  plugins {
    create("grpc") {
      artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
    }
//    create("grpckt") {
//      artifact = "io.grpc:protoc-gen-grpc-kotlin:grpcKotlinVersion:jdk8@jar"
//    }
  }

  generateProtoTasks {
    all().forEach {
      it.plugins {
        create("grpc") {}
//          create("grpckt") {
//            option("lite")
//          }
      }
      it.builtins {
        named("java") {}
//          create("kotlin") {
//            option("lite")
//          }
      }
    }
  }
}
