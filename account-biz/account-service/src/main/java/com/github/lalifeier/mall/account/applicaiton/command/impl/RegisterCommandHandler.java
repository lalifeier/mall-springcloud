package com.github.lalifeier.mall.account.applicaiton.command.impl;

import com.github.lalifeier.mall.account.applicaiton.command.RegisterCommand;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountPassword;
import com.github.lalifeier.mall.account.domain.account.service.AccountDomainService;
import com.github.lalifeier.mall.account.interfaces.web.model.request.RegisterRequest;
import com.github.lalifeier.mall.account.interfaces.web.model.response.RegisterResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RegisterCommandHandler implements RegisterCommand {

  @Resource
  private AccountDomainService accountDomainService;

  @Override
  public RegisterResponse execute(RegisterRequest request) {
    String username = request.getUsername();
    String password = request.getPassword();
    Long id = accountDomainService.register(new AccountName(username), new AccountPassword(password));

    return RegisterResponse.builder()
      .id(id)
      .build();
  }
}
