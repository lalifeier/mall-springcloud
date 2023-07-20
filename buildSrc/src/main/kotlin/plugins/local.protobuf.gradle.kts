plugins {
  id("com.google.protobuf")
}

val protocVersion = "3.22.2"
val protocKotlinVersion = "1.3.0"

protobuf {
  protoc {
    artifact = "com.google.protobuf:protoc:$protocVersion"
  }

  plugins {
    create("grpc") {
      artifact = "io.grpc:protoc-gen-grpc-java:$protocVersion"
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
