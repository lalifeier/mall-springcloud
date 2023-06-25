package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.provider.login;

import com.github.lalifeier.mall.cloud.account.applicaiton.account.dto.AccountDTO;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.dto.login.LoginDTO;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.dto.login.LoginRespDTO;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginType;

public abstract class AbstractLoginProvider implements LoginProvider {


  protected abstract void preAuthenticationCheck(LoginDTO loginDTO);

  protected abstract AccountDTO authenticate(LoginDTO loginDTO);

  @Override
  public LoginRespDTO login(LoginDTO loginDTO) {
    preAuthenticationCheck(loginDTO);

    AccountDTO accountDTO = authenticate(loginDTO);

    return null;
  }

  @Override
  public abstract boolean supports(LoginType loginType);
}
