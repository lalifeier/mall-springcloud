package com.github.lalifeier.mall.cloud.account.interfaces.rest.factory;

import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.EmailLoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.LoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.PhoneLoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.UsernameLoginCommand;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginType;
import com.github.lalifeier.mall.cloud.account.model.request.LoginRequest;
import java.util.Optional;

public class LoginCommandFactory {
    public static LoginCommand getLoginCommand(LoginRequest request) {
        String type = request.getType();
        LoginType loginType =
                Optional.ofNullable(type)
                        .map(String::toUpperCase)
                        .map(LoginType::parse)
                        .orElseThrow(() -> new RuntimeException("loginType must be provided"));

        return switch (loginType) {
            case USERNAME -> new UsernameLoginCommand(request.getUsername(), request.getPassword());
            case EMAIL -> new EmailLoginCommand(request.getPhone(), request.getPassword());
            case PHONE -> new PhoneLoginCommand(request.getEmail(), request.getCode());
        };
    }
}
