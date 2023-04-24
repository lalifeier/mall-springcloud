package com.github.lalifeier.mall.account.applicaiton.command.impl;

import javax.annotation.Resource;

import com.github.lalifeier.mall.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.account.infrastructure.error.LoginErrorCodes;
import com.github.lalifeier.mall.account.infrastructure.error.RegisterErrorCodes;
import com.github.lalifeier.mall.account.infrastructure.error.RegisterException;
import org.springframework.stereotype.Component;

import com.github.lalifeier.mall.account.applicaiton.command.LoginCommand;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountPassword;
import com.github.lalifeier.mall.account.domain.account.service.AccountDomainService;
import com.github.lalifeier.mall.account.interfaces.dto.request.LoginRequest;
import com.github.lalifeier.mall.account.interfaces.dto.response.LoginResponse;

@Component
public class LoginCommandHandler implements LoginCommand {

  @Resource
  private AccountDomainService accountDomainService;

  @Resource
  private AccountRepository accountRepository;

  @Override
  public LoginResponse execute(LoginRequest request) {

    AccountName name = new AccountName(request.getUsername());
    AccountPassword password = new AccountPassword(request.getPassword());

    Account account = accountRepository.find(name);
    if (account == null) {
      throw new RegisterException(LoginErrorCodes.USER_NOT_EXIST);
    }

    if (!account.getPassword().verifyPassword(password.getEncryptPassword())) {
      throw new RegisterException(LoginErrorCodes.PASSWORD_ERROR);
    }

    return null;
  }
}
