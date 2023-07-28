dependencies {
  runtimeOnly(libs.grpc.netty.shaded)
  implementation(libs.grpc.protobuf)
  implementation(libs.grpc.stub)
  if (JavaVersion.current().isJava9Compatible()) {
    // Workaround for @javax.annotation.Generated
    // see: https://github.com/grpc/grpc-java/issues/3633
//    compileOnly(libs.annotation.api)
    compileOnly("javax.annotation:javax.annotation-api:1.3.2")
  }

//  implementation(libs.grpc.kotlin.stub)
//  implementation(libs.protobuf.kotlin)
}
