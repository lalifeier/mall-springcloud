package com.github.lalifeier.mall.account.applicaiton.command;

import com.github.lalifeier.mall.account.interfaces.facade.web.model.request.LoginRequest;
import com.github.lalifeier.mall.account.interfaces.facade.web.model.response.AccountVO;

public interface LoginCommand {
  AccountVO execute(LoginRequest request);
}
