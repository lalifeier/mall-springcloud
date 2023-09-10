package com.github.lalifeier.mall.cloud.account.api.authentication;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.lalifeier.mall.cloud.account.api.authentication.model.command.RegisterCommand;
import com.github.lalifeier.mall.cloud.account.api.authentication.model.dto.LoginDTO;
import com.github.lalifeier.mall.cloud.account.api.authentication.model.dto.RegisterDTO;
import com.github.lalifeier.mall.cloud.account.api.authentication.model.request.LoginRequest;
import com.github.lalifeier.mall.cloud.common.enums.StatusEnum;

public interface AuthenticationApi {
  @PostMapping("/login")
  LoginDTO login(@Validated @RequestBody LoginRequest request);

  @PostMapping("/register")
  RegisterDTO register(@Validated @RequestBody RegisterCommand command);


  @GetMapping("/getEnum")
  StatusEnum getEnum(@RequestParam("status") StatusEnum status);
}
