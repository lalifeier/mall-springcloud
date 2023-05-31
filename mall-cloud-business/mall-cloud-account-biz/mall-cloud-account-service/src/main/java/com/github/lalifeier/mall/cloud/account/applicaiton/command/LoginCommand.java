package com.github.lalifeier.mall.cloud.account.applicaiton.command;

import com.github.lalifeier.mall.cloud.account.facade.web.model.request.LoginRequest;
import com.github.lalifeier.mall.cloud.account.facade.web.model.response.AccountVO;

public interface LoginCommand {
  AccountVO execute(LoginRequest request);
}
