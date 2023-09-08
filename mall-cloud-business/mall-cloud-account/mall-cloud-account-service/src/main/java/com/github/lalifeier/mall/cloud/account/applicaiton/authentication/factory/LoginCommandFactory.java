package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.factory;

import com.github.lalifeier.mall.cloud.account.api.authentication.enums.LoginTypeEnum;
import com.github.lalifeier.mall.cloud.account.api.authentication.model.request.LoginRequest;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.EmailLoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.LoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.PhoneLoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.UsernameLoginCommand;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginErrorCodeEnum;
import com.github.lalifeier.mall.cloud.account.infrastructure.exception.LoginException;

public class LoginCommandFactory {
    public static LoginCommand getLoginCommand(LoginRequest request) {
        LoginTypeEnum loginTypeEnum = request.getType();

        if (loginTypeEnum == null) {
            throw new LoginException(LoginErrorCodeEnum.B_LOGIN_TYPE_ERROR);
        }

        return switch (loginTypeEnum) {
            case USERNAME -> new UsernameLoginCommand(request.getUsername(), request.getPassword());
            case EMAIL -> new EmailLoginCommand(request.getPhone(), request.getPassword());
            case PHONE -> new PhoneLoginCommand(request.getEmail(), request.getCode());
        };
    }
}
