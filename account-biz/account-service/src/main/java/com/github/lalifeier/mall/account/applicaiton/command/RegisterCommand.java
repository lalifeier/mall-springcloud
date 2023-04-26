package com.github.lalifeier.mall.account.applicaiton.command;

import com.github.lalifeier.mall.account.interfaces.facade.web.model.request.RegisterRequest;

public interface RegisterCommand {
  RegisterResponseVO execute(RegisterRequest request);
}
