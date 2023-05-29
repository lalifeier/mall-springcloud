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
