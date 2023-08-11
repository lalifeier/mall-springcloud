package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.provider;

import com.github.lalifeier.mall.cloud.account.api.authentication.model.dto.LoginDTO;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.assembler.AuthenticationAssembler;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.LoginCommand;
import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginTypeEnum;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractLoginProvider implements LoginProvider {

    private final AuthenticationAssembler authenticationAssembler =
            AuthenticationAssembler.INSTANCE;

    protected abstract void preAuthenticationCheck(LoginCommand loginCommand);

    protected abstract Account authenticate(LoginCommand loginCommand);

    @Override
    public LoginDTO login(LoginCommand loginCommand) {
        preAuthenticationCheck(loginCommand);

        Account account = authenticate(loginCommand);

        return authenticationAssembler.toLoginDTO(account);
    }

    @Override
    public abstract boolean supports(LoginTypeEnum loginTypeEnum);
}
