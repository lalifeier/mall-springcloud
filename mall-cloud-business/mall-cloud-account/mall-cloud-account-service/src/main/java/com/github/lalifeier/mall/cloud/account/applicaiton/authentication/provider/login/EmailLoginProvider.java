package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.provider.login;

import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginType;

public class EmailLoginProvider extends AbstractLoginProvider {
  @Override
  public boolean supports(LoginType loginType) {
    return loginType.equals(LoginType.EMAIL);
  }
}
