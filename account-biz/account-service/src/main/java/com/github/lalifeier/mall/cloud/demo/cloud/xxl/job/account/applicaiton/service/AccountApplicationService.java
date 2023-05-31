package com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.account.applicaiton.service;

import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.account.interfaces.facade.web.model.request.LoginRequest;
import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.account.interfaces.facade.web.model.response.AccountVO;

public interface AccountApplicationService {
  //RegisterResponseVO register(RegisterRequest request);

  AccountVO login(LoginRequest request);
}
