dependencies {
  runtimeOnly(libs.grpc.netty.shaded)
  implementation(libs.grpc.protobuf)
  implementation(libs.grpc.stub)
  compileOnly(libs.annotations.api) // necessary for Java 9+
}
