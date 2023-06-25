package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.dto.login;

import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginType;
import lombok.Data;

@Data
public class UsernameLoginDTO extends LoginDTO {
  private final String username;
  private final String password;

  @Override
  public LoginType getLoginType() {
    return LoginType.USERNAME;
  }
}
