package com.github.lalifeier.mall.account.applicaiton.command.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.lalifeier.mall.account.applicaiton.command.LoginCommand;
import com.github.lalifeier.mall.account.domain.account.service.AccountDomainService;
import com.github.lalifeier.mall.account.interfaces.web.model.request.LoginRequest;
import com.github.lalifeier.mall.account.interfaces.web.model.response.LoginResponse;

@Component
public class LoginCommandHandler implements LoginCommand {

  @Resource
  private AccountDomainService accountDomainService;

  @Override
  public LoginResponse execute(LoginRequest request) {
    accountDomainService.login(request.getUsername(), request.getPassword());
    return null;
  }
}
