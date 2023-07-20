dependencies {
  runtimeOnly(libs.grpc.netty.shaded)
  implementation(libs.grpc.protobuf)
  implementation(libs.grpc.stub)
  if (JavaVersion.current().isJava9Compatible()) {
    // Workaround for @javax.annotation.Generated
    // see: https://github.com/grpc/grpc-java/issues/3633
    compileOnly("jakarta.annotation:jakarta.annotation-api")
  }

//  implementation(libs.grpc.kotlin.stub)
//  implementation(libs.protobuf.kotlin)
}
