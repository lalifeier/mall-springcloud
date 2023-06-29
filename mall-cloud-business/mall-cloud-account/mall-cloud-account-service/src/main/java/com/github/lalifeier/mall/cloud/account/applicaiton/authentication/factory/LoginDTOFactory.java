package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.factory;

import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.EmailLoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.LoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.PhoneLoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.UsernameLoginCommand;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginType;

import java.util.Map;
import java.util.Optional;

public class LoginDTOFactory {
  public static LoginCommand getLoginDTO(Map<String, String> parameters) {
    String loginTypeStr = parameters.get("loginType");
    LoginType loginType = Optional.ofNullable(loginTypeStr)
      .map(String::toUpperCase)
      .map(LoginType::parse)
      .orElseThrow(() -> new RuntimeException("loginType must be provided"));

    return switch (loginType) {
      case USERNAME -> new UsernameLoginCommand(parameters.get("userName"), parameters.get("password"));
      case PHONE -> new PhoneLoginCommand(parameters.get("phone"), parameters.get("code"));
      case EMAIL -> new EmailLoginCommand(parameters.get("email"), parameters.get("code"));
    };
  }
}
