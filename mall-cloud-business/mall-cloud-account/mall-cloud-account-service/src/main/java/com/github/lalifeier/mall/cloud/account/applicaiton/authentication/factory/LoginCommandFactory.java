package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.factory;

import com.github.lalifeier.mall.cloud.account.api.authentication.model.request.LoginRequest;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.EmailLoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.LoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.PhoneLoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.UsernameLoginCommand;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginErrorCodeEnum;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginTypeEnum;
import com.github.lalifeier.mall.cloud.account.infrastructure.exception.LoginException;
import java.util.Optional;

public class LoginCommandFactory {
  public static LoginCommand getLoginCommand(LoginRequest request) {
    String type = request.getType();
    LoginTypeEnum loginTypeEnum =
        Optional.ofNullable(type).map(String::toUpperCase).map(LoginTypeEnum::parse)
            .orElseThrow(() -> new LoginException(LoginErrorCodeEnum.B_LOGIN_NOT_EXIST));

    return switch (loginTypeEnum) {
      case USERNAME -> new UsernameLoginCommand(request.getUsername(), request.getPassword());
      case EMAIL -> new EmailLoginCommand(request.getPhone(), request.getPassword());
      case PHONE -> new PhoneLoginCommand(request.getEmail(), request.getCode());
    };
  }
}
