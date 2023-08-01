package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command;


import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UsernameLoginCommand extends LoginCommand {
  private final String username;
  private final String password;

  @Override
  public LoginTypeEnum getLoginType() {
    return LoginTypeEnum.USERNAME;
  }
}
