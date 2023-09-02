package com.github.lalifeier.mall.cloud.account.infrastructure.enums;

import com.github.lalifeier.mall.cloud.account.infrastructure.exception.LoginException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum LoginTypeEnum {
  USERNAME, EMAIL, PHONE;

  public static LoginTypeEnum parse(String loginType) {
    try {
      return LoginTypeEnum.valueOf(loginType.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new LoginException(LoginErrorCodeEnum.B_LOGIN_TYPE_ERROR);
    }
  }
}
