dependencies {
  runtimeOnly("io.grpc:grpc-netty-shaded")
  implementation("io.grpc:grpc-protobuf")
  implementation("io.grpc:grpc-stub")
  if (JavaVersion.current().isJava9Compatible()) {
    // Workaround for @javax.annotation.Generated
    // see: https://github.com/grpc/grpc-java/issues/3633
    compileOnly("jakarta.annotation:jakarta.annotation-api")
  }

  implementation("io.grpc:grpc-kotlin-stub")
  implementation("com.google.protobuf:protobuf-kotlin")
}
