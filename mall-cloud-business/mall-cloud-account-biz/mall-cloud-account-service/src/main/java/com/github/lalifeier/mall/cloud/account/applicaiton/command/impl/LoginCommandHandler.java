package com.github.lalifeier.mall.cloud.account.applicaiton.command.impl;

import com.github.lalifeier.mall.cloud.account.applicaiton.command.LoginCommand;
import com.github.lalifeier.mall.cloud.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.cloud.account.domain.account.service.AccountDomainService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LoginCommandHandler implements LoginCommand {

  @Resource
  private AccountDomainService accountDomainService;

  @Resource
  private AccountRepository accountRepository;

  //@Override
  //public AccountVO execute(LoginRequest request) {
  //
  //  AccountName name = new AccountName(request.getUsername());
  //  AccountPassword password = new AccountPassword(request.getPassword());
  //
  //  AccountDO account = accountRepository.find(name);
  //  if (account == null) {
  //    throw new RegisterException(LoginErrorCodes.USER_NOT_EXIST);
  //  }
  //
  //  if (!account.getPassword().verifyPassword(password.getEncryptPassword())) {
  //    throw new RegisterException(LoginErrorCodes.PASSWORD_ERROR);
  //  }
  //
  //  return null;
  //}
}
