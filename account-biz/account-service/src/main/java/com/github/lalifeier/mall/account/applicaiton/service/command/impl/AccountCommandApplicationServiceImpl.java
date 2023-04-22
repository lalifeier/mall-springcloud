package com.github.lalifeier.mall.account.applicaiton.service.command.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.lalifeier.mall.account.applicaiton.service.command.AccountCommandApplicationService;
import com.github.lalifeier.mall.account.domain.account.service.AccountDomainService;
import com.github.lalifeier.mall.account.interfaces.web.model.request.LoginRequest;
import com.github.lalifeier.mall.account.interfaces.web.model.request.RegisterRequest;

@Service
public class AccountCommandApplicationServiceImpl implements AccountCommandApplicationService {

  @Resource
  private AccountDomainService accountDomainService;

  @Override
  public long register(RegisterRequest req) {
    return accountDomainService.register(req.getUsername(),
        req.getPassword());
  }

  @Override
  public void login(LoginRequest req) {
    accountDomainService.login(req.getUsername(), req.getPassword());
  }

}
