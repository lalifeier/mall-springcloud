package com.github.lalifeier.mall.demo.interfaces.grpc;

import com.github.lalifeier.mall.demo.grpc.api.HelloReply;
import com.github.lalifeier.mall.demo.grpc.api.HelloRequest;
import com.github.lalifeier.mall.demo.grpc.api.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloService extends HelloServiceGrpc.HelloServiceImplBase {
  @Override
  public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
    HelloReply reply = HelloReply.newBuilder()
      .setMessage("Hello " + request.getName())
      .build();
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }
}
