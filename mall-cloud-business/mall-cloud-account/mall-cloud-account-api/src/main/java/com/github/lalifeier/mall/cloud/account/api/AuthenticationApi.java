package com.github.lalifeier.mall.cloud.account.api;

import com.github.lalifeier.mall.cloud.account.model.request.LoginRequest;
import com.github.lalifeier.mall.cloud.account.model.request.RegisterRequest;
import com.github.lalifeier.mall.cloud.account.model.response.LoginResponse;
import com.github.lalifeier.mall.cloud.account.model.response.RegisterResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationApi {

  @PostMapping("/login")
  LoginResponse login(@Validated @RequestBody LoginRequest request);

  @PostMapping("/register")
  RegisterResponse register(@Validated @RequestBody RegisterRequest request);
}
