package com.github.lalifeier.mall.account.applicaiton.command;

import com.github.lalifeier.mall.account.interfaces.web.model.request.RegisterRequest;
import com.github.lalifeier.mall.account.interfaces.web.model.response.RegisterResponse;

public interface RegisterCommand {
  RegisterResponse execute(RegisterRequest request);
}
