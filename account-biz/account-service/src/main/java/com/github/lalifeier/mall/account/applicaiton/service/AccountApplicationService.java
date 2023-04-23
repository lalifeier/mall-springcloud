package com.github.lalifeier.mall.account.applicaiton.service;

import com.github.lalifeier.mall.account.interfaces.web.model.request.LoginRequest;
import com.github.lalifeier.mall.account.interfaces.web.model.request.RegisterRequest;
import com.github.lalifeier.mall.account.interfaces.web.model.response.LoginResponse;
import com.github.lalifeier.mall.account.interfaces.web.model.response.RegisterResponse;

public interface AccountApplicationService {
  RegisterResponse register(RegisterRequest request);

  LoginResponse login(LoginRequest request);
}
