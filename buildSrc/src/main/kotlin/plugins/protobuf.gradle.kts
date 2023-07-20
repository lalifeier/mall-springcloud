plugins {
  id("com.google.protobuf")
}

protobuf {
  protoc {
    artifact = "com.google.protobuf:protoc:3.22.2"
  }

  plugins {
    create("grpc") {
      artifact = "io.grpc:protoc-gen-grpc-java:3.22.2"
    }
//      create("grpckt") {
//        artifact = "io.grpc:protoc-gen-grpc-kotlin:${rootProject.libs.versions.grpc.kotlin.get()}:jdk8@jar"
//      }
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
