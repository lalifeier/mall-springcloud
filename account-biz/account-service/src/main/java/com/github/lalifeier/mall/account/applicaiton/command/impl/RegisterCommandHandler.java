package com.github.lalifeier.mall.account.applicaiton.command.impl;

import com.github.lalifeier.mall.account.applicaiton.command.RegisterCommand;
import com.github.lalifeier.mall.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountPassword;
import com.github.lalifeier.mall.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.account.domain.account.service.AccountDomainService;
import com.github.lalifeier.mall.account.infrastructure.error.RegisterErrorCodes;
import com.github.lalifeier.mall.account.infrastructure.error.RegisterException;
import com.github.lalifeier.mall.account.interfaces.dto.request.RegisterRequest;
import com.github.lalifeier.mall.account.interfaces.dto.response.RegisterResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RegisterCommandHandler implements RegisterCommand {

  @Resource
  private AccountDomainService accountDomainService;

  @Resource
  private AccountRepository accountRepository;

  @Override
  public RegisterResponse execute(RegisterRequest request) {
    AccountName name = new AccountName(request.getUsername());
    AccountPassword password = new AccountPassword(request.getPassword());

    if (accountRepository.find(name) != null) {
      throw new RegisterException(RegisterErrorCodes.USER_EXIST);
    }

    Account account =  Account.createAccount(name, password);

    accountRepository.save(account);

    return RegisterResponse.builder()
      .id(account.getId().getValue())
      .build();
  }
}
