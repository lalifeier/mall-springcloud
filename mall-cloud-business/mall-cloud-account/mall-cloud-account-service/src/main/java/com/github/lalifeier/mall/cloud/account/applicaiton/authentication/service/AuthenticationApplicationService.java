package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.service;

import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.dto.login.LoginDTO;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.dto.login.LoginRespDTO;

public interface AuthenticationApplicationService {
  LoginRespDTO login(LoginDTO loginDTO);
}
