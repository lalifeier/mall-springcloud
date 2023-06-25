package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.dto.login;

import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginType;
import lombok.Data;

@Data
public class EmailLoginDTO extends LoginDTO {
  private final String email;
  private final String code;

  @Override
  public LoginType getLoginType() {
    return LoginType.EMAIL;
  }
}
