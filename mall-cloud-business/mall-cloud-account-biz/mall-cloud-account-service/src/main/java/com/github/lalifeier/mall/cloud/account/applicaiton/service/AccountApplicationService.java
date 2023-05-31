package com.github.lalifeier.mall.cloud.account.applicaiton.service;

import com.github.lalifeier.mall.cloud.account.facade.web.model.request.LoginRequest;
import com.github.lalifeier.mall.cloud.account.facade.web.model.response.AccountVO;

public interface AccountApplicationService {
  //RegisterResponseVO register(RegisterRequest request);

  AccountVO login(LoginRequest request);
}
