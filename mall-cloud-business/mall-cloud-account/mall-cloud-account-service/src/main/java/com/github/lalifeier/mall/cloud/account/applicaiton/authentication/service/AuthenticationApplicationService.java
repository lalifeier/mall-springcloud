package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.service;

import com.github.lalifeier.mall.cloud.account.api.authentication.model.command.RegisterCommand;
import com.github.lalifeier.mall.cloud.account.api.authentication.model.dto.LoginDTO;
import com.github.lalifeier.mall.cloud.account.api.authentication.model.dto.RegisterDTO;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.LoginCommand;

public interface AuthenticationApplicationService {

    RegisterDTO register(RegisterCommand command);

    LoginDTO login(LoginCommand command);

    // void logout();
}
