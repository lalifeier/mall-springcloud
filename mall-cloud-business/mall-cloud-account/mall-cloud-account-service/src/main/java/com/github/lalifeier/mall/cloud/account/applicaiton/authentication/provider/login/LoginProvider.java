package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.provider.login;

import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.LoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.dto.LoginDTO;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginType;

public interface LoginProvider {

  LoginDTO login(LoginCommand loginCommand);

  boolean supports(LoginType loginType);
}
