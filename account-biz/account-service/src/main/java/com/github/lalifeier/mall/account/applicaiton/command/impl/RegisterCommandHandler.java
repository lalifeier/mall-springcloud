package com.github.lalifeier.mall.account.applicaiton.command.impl;

import com.github.lalifeier.mall.account.applicaiton.command.RegisterCommand;
import com.github.lalifeier.mall.account.domain.account.model.entity.AccountDO;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountPassword;
import com.github.lalifeier.mall.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.account.domain.account.service.AccountDomainService;
import com.github.lalifeier.mall.account.infrastructure.exception.RegisterErrorCodes;
import com.github.lalifeier.mall.account.infrastructure.exception.RegisterException;
import com.github.lalifeier.mall.account.interfaces.facade.web.model.request.RegisterRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RegisterCommandHandler implements RegisterCommand {

  @Resource
  private AccountDomainService accountDomainService;

  @Resource
  private AccountRepository accountRepository;

  //@Override
  //public RegisterResponseVO execute(RegisterRequest request) {
  //  AccountName name = new AccountName(request.getUsername());
  //  AccountPassword password = new AccountPassword(request.getPassword());
  //
  //  if (accountRepository.find(name) != null) {
  //    throw new RegisterException(RegisterErrorCodes.USER_EXIST);
  //  }
  //
  //  AccountDO account =  AccountDO.createAccount(name, password);
  //
  //  accountRepository.save(account);
  //
  //  return RegisterResponseVO.builder()
  //    .id(account.getId().getValue())
  //    .build();
  //}
}
