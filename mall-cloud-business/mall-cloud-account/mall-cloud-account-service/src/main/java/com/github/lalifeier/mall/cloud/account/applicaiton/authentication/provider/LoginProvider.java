package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.provider;


import com.github.lalifeier.mall.cloud.account.api.authentication.model.dto.LoginDTO;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.LoginCommand;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginTypeEnum;

public interface LoginProvider {

  LoginDTO login(LoginCommand loginCommand);

  boolean supports(LoginTypeEnum loginTypeEnum);
}
