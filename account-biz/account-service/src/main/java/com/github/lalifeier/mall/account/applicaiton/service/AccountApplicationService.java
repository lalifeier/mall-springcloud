package com.github.lalifeier.mall.account.applicaiton.service;

import com.github.lalifeier.mall.account.interfaces.facade.web.model.request.LoginRequest;
import com.github.lalifeier.mall.account.interfaces.facade.web.model.request.RegisterRequest;
import com.github.lalifeier.mall.account.interfaces.facade.web.model.response.AccountVO;

public interface AccountApplicationService {
  RegisterResponseVO register(RegisterRequest request);

  AccountVO login(LoginRequest request);
}
