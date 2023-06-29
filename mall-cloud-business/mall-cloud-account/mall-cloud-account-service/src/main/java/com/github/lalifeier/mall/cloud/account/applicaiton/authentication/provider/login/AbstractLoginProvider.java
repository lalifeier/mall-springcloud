package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.provider.login;

import com.github.lalifeier.mall.cloud.account.applicaiton.account.dto.AccountDTO;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.LoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.dto.LoginDTO;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginType;

public abstract class AbstractLoginProvider implements LoginProvider {


  protected abstract void preAuthenticationCheck(LoginCommand loginCommand);

  protected abstract AccountDTO authenticate(LoginCommand loginCommand);

  @Override
  public LoginDTO login(LoginCommand loginCommand) {
    preAuthenticationCheck(loginCommand);

    AccountDTO accountDTO = authenticate(loginCommand);

    return null;
  }

  @Override
  public abstract boolean supports(LoginType loginType);
}
