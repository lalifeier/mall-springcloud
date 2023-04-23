package com.github.lalifeier.mall.account.applicaiton.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.lalifeier.mall.account.applicaiton.command.impl.LoginCommandHandler;
import com.github.lalifeier.mall.account.applicaiton.command.impl.RegisterCommandHandler;
import com.github.lalifeier.mall.account.applicaiton.service.AccountApplicationService;
import com.github.lalifeier.mall.account.interfaces.web.model.request.LoginRequest;
import com.github.lalifeier.mall.account.interfaces.web.model.request.RegisterRequest;
import com.github.lalifeier.mall.account.interfaces.web.model.response.LoginResponse;
import com.github.lalifeier.mall.account.interfaces.web.model.response.RegisterResponse;

@Service
public class AccountApplicationServiceImpl implements AccountApplicationService {

  @Resource
  private RegisterCommandHandler registerCommandHandler;

  @Resource
  private LoginCommandHandler loginCommandHandler;

  @Override
  public RegisterResponse register(RegisterRequest request) {
    return registerCommandHandler.execute(request);
  }

  @Override
  public LoginResponse login(LoginRequest request) {
    return loginCommandHandler.execute(request);
  }
}
