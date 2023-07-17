package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command;

import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class EmailLoginCommand extends LoginCommand {
  private final String email;
  private final String password;

  @Override
  public LoginType getLoginType() {
    return LoginType.EMAIL;
  }
}
