package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command;

import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginType;
import lombok.Data;

@Data
public class UsernameLoginCommand extends LoginCommand {
  private final String username;
  private final String password;

  @Override
  public LoginType getLoginType() {
    return LoginType.USERNAME;
  }
}