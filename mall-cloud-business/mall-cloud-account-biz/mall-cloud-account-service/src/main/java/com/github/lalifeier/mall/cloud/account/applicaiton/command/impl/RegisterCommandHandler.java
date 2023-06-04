package com.github.lalifeier.mall.cloud.account.applicaiton.command.impl;

import com.github.lalifeier.mall.cloud.account.applicaiton.command.RegisterCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.converter.AccountConverter;
import com.github.lalifeier.mall.cloud.account.applicaiton.dto.AccountBO;
import com.github.lalifeier.mall.cloud.account.applicaiton.dto.RegisterBO;
import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.AccountDO;
import com.github.lalifeier.mall.cloud.account.domain.account.service.AccountDomainService;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.RegisterType;
import org.springframework.stereotype.Component;

@Component
public class RegisterCommandHandler implements RegisterCommand {
  private final AccountDomainService accountDomainService;
  private final AccountConverter accountConverter = AccountConverter.INSTANCE;

  public RegisterCommandHandler(AccountDomainService accountDomainService) {
    this.accountDomainService = accountDomainService;
  }

  @Override
  public AccountBO execute(RegisterBO registerBO) {
    RegisterType registerType = Enum.valueOf(RegisterType.class, registerBO.getType());

    AccountDO accountDO = accountConverter.convert(registerBO);

    accountDO = accountDomainService.register(registerType, accountDO);

    return accountConverter.convert(accountDO);
  }

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
