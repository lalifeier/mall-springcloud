package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.factory;

import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.dto.login.EmailLoginDTO;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.dto.login.LoginDTO;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.dto.login.PhoneLoginDTO;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.dto.login.UsernameLoginDTO;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginType;

import java.util.Map;
import java.util.Optional;

public class LoginDTOFactory {
  public static LoginDTO getLoginDTO(Map<String, String> parameters) {
    String loginTypeStr = parameters.get("loginType");
    LoginType loginType = Optional.ofNullable(loginTypeStr)
      .map(String::toUpperCase)
      .map(LoginType::parse)
      .orElseThrow(() -> new RuntimeException("loginType must be provided"));

    return switch (loginType) {
      case USERNAME -> new UsernameLoginDTO(parameters.get("userName"), parameters.get("password"));
      case PHONE -> new PhoneLoginDTO(parameters.get("phone"), parameters.get("code"));
      case EMAIL -> new EmailLoginDTO(parameters.get("email"), parameters.get("code"));
    };
  }
}
