package com.github.lalifeier.mall.account.applicaiton.command;

import com.github.lalifeier.mall.account.interfaces.web.model.request.LoginRequest;
import com.github.lalifeier.mall.account.interfaces.web.model.response.LoginResponse;

public interface LoginCommand {
  LoginResponse execute(LoginRequest request);
}
