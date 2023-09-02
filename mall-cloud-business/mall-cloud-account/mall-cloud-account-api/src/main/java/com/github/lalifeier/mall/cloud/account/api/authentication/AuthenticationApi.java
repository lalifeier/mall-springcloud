package com.github.lalifeier.mall.cloud.account.api.authentication;

import com.github.lalifeier.mall.cloud.account.api.authentication.model.command.RegisterCommand;
import com.github.lalifeier.mall.cloud.account.api.authentication.model.dto.LoginDTO;
import com.github.lalifeier.mall.cloud.account.api.authentication.model.dto.RegisterDTO;
import com.github.lalifeier.mall.cloud.account.api.authentication.model.request.LoginRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationApi {
  @PostMapping("/login")
  LoginDTO login(@Validated @RequestBody LoginRequest request);

  @PostMapping("/register")
  RegisterDTO register(@Validated @RequestBody RegisterCommand command);
}
