package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.provider.login;

import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.dto.login.LoginDTO;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.dto.login.LoginRespDTO;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginType;

public interface LoginProvider {

  LoginRespDTO login(LoginDTO loginDTO);

  boolean supports(LoginType loginType);
}
