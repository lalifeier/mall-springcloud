package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.service;

import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.LoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.dto.LoginDTO;

public interface AuthenticationApplicationService {

    //  void register();
    LoginDTO login(LoginCommand loginCommand);

    //  void logout();
}
