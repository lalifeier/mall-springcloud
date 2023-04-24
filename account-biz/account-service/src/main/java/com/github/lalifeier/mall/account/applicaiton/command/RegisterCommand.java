package com.github.lalifeier.mall.account.applicaiton.command;

import com.github.lalifeier.mall.account.interfaces.dto.request.RegisterRequest;
import com.github.lalifeier.mall.account.interfaces.dto.response.RegisterResponse;

public interface RegisterCommand {
  RegisterResponse execute(RegisterRequest request);
}
