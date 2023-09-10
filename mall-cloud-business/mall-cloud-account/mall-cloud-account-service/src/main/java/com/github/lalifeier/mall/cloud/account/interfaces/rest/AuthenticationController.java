package com.github.lalifeier.mall.cloud.account.interfaces.rest;

import com.github.lalifeier.mall.cloud.account.api.authentication.AuthenticationApi;
import com.github.lalifeier.mall.cloud.account.api.authentication.model.command.RegisterCommand;
import com.github.lalifeier.mall.cloud.account.api.authentication.model.dto.LoginDTO;
import com.github.lalifeier.mall.cloud.account.api.authentication.model.dto.RegisterDTO;
import com.github.lalifeier.mall.cloud.account.api.authentication.model.request.LoginRequest;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.factory.LoginCommandFactory;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.LoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.service.AuthenticationApplicationService;
import com.github.lalifeier.mall.cloud.common.enums.StatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class AuthenticationController implements AuthenticationApi {

    private final AuthenticationApplicationService authenticationApplicationService;

    @Override
    public LoginDTO login(LoginRequest request) {
        LoginCommand command = LoginCommandFactory.getLoginCommand(request);
        return authenticationApplicationService.login(command);
    }

    @Override
    public RegisterDTO register(RegisterCommand command) {
        return authenticationApplicationService.register(command);
    }

    @Override
    public StatusEnum getEnum(StatusEnum status) {
        return status;
    }
}
