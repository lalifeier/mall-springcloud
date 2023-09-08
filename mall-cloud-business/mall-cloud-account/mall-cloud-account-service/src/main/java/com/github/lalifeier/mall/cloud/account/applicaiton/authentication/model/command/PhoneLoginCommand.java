package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command;

import com.github.lalifeier.mall.cloud.account.api.authentication.enums.LoginTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PhoneLoginCommand extends LoginCommand {
    private final String phone;
    private final String code;

    @Override
    public LoginTypeEnum getLoginType() {
        return LoginTypeEnum.PHONE;
    }
}
