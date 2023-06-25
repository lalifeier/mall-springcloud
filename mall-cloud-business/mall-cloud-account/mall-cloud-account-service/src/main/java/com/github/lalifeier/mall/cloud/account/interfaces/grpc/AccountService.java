package com.github.lalifeier.mall.cloud.account.interfaces.grpc;

//@GrpcService
//public class AccountService extends AccountServiceGrpc.AccountServiceImplBase {

//  @Resource
//  private RegisterCommandHandler registerCommandHandler;
//
//  private final AccountConverter accountConverter = AccountConverter.INSTANCE;
//
//  @Override
//  public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
//    //super.login(request, responseObserver);
//  }
//
//  @Override
//  public void register(RegisterRequest request, StreamObserver<RegisterResponse> responseObserver) {
//    RegisterDTO registerBO = accountConverter.convert(request);
//
//    AccountBO accountBO = registerCommandHandler.execute(registerBO);
//
//    RegisterResponse reply = accountConverter.convert(accountBO);
//
//    responseObserver.onNext(reply);
//    responseObserver.onCompleted();
//  }
//}
