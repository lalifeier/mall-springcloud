package com.github.lalifeier.mall.account.applicaiton.command;

import com.github.lalifeier.mall.account.interfaces.dto.request.LoginRequest;
import com.github.lalifeier.mall.account.interfaces.dto.response.LoginResponse;

public interface LoginCommand {
  LoginResponse execute(LoginRequest request);
}
