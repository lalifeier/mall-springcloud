package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.provider;

import com.github.lalifeier.mall.cloud.account.api.authentication.enums.LoginTypeEnum;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.LoginCommand;
import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class PhoneLoginProvider extends AbstractLoginProvider {

    @Override
    protected void preAuthenticationCheck(LoginCommand loginCommand) {}

    @Override
    protected Account authenticate(LoginCommand loginCommand) {
        return null;
    }

    @Override
    public boolean supports(LoginTypeEnum loginTypeEnum) {
        return loginTypeEnum.equals(LoginTypeEnum.PHONE);
    }
}
