package com.github.lalifeier.mall.cloud.account.interfaces.grpc;

import com.github.lalifeier.mall.cloud.account.applicaiton.command.impl.RegisterCommandHandler;
import com.github.lalifeier.mall.cloud.account.applicaiton.dto.AccountBO;
import com.github.lalifeier.mall.cloud.account.applicaiton.dto.RegisterBO;
import com.github.lalifeier.mall.cloud.account.interfaces.grpc.converter.AccountConverter;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import javax.annotation.Resource;

@GrpcService
public class AccountService extends AccountServiceGrpc.AccountServiceImplBase {

  @Resource
  private RegisterCommandHandler registerCommandHandler;

  private final AccountConverter accountConverter = AccountConverter.INSTANCE;

  @Override
  public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
    //super.login(request, responseObserver);
  }

  @Override
  public void register(RegisterRequest request, StreamObserver<RegisterResponse> responseObserver) {
    RegisterBO registerBO = accountConverter.convert(request);

    AccountBO accountBO = registerCommandHandler.execute(registerBO);

    RegisterResponse reply = accountConverter.convert(accountBO);

    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }
}
