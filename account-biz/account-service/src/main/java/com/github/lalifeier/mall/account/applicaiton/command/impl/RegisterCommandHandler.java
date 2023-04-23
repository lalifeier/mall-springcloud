package com.github.lalifeier.mall.account.applicaiton.command.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.lalifeier.mall.account.applicaiton.command.RegisterCommand;
import com.github.lalifeier.mall.account.domain.account.service.AccountDomainService;
import com.github.lalifeier.mall.account.interfaces.web.model.request.RegisterRequest;
import com.github.lalifeier.mall.account.interfaces.web.model.response.RegisterResponse;

@Component
public class RegisterCommandHandler implements RegisterCommand {

  @Resource
  private AccountDomainService accountDomainService;

  @Override
  public RegisterResponse execute(RegisterRequest request) {
    Long id = accountDomainService.register(request.getUsername(),
        request.getPassword());

    return RegisterResponse.builder()
        .id(id)
        .build();
  }
}
