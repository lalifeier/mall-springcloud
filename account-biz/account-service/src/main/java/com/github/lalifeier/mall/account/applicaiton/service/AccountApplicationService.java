package com.github.lalifeier.mall.account.applicaiton.service;

import com.github.lalifeier.mall.account.interfaces.dto.request.LoginRequest;
import com.github.lalifeier.mall.account.interfaces.dto.request.RegisterRequest;
import com.github.lalifeier.mall.account.interfaces.dto.response.LoginResponse;
import com.github.lalifeier.mall.account.interfaces.dto.response.RegisterResponse;

public interface AccountApplicationService {
  RegisterResponse register(RegisterRequest request);

  LoginResponse login(LoginRequest request);
}
