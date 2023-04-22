package com.github.lalifeier.mall.account.applicaiton.service.command;

import com.github.lalifeier.mall.account.interfaces.web.model.request.LoginRequest;
import com.github.lalifeier.mall.account.interfaces.web.model.request.RegisterRequest;

public interface AccountCommandApplicationService {
  long register(RegisterRequest req);

  void login(LoginRequest req);
}
