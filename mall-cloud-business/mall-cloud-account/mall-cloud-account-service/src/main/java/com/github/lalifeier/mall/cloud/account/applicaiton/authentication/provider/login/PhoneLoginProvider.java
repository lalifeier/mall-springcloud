package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.provider.login;

import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.LoginCommand;
import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginType;

public class PhoneLoginProvider extends AbstractLoginProvider {
  

  @Override
  protected void preAuthenticationCheck(LoginCommand loginCommand) {

  }

  @Override
  protected Account authenticate(LoginCommand loginCommand) {
    return null;
  }

  @Override
  public boolean supports(LoginType loginType) {
    return loginType.equals(LoginType.PHONE);
  }
}
