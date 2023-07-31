package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.provider.login;

import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.LoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.UsernameLoginCommand;
import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountPassword;
import com.github.lalifeier.mall.cloud.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginType;
import com.github.lalifeier.mall.cloud.account.infrastructure.exception.code.LoginErrorCode;
import com.github.lalifeier.mall.cloud.common.exception.BusinessException;
import jakarta.annotation.Resource;

public class UsernameLoginProvider extends AbstractLoginProvider {

    @Resource private AccountRepository accountRepository;

    @Override
    protected void preAuthenticationCheck(LoginCommand loginCommand) {
        UsernameLoginCommand usernameLoginDTO = (UsernameLoginCommand) loginCommand;
        String username = usernameLoginDTO.getUsername();
        String password = usernameLoginDTO.getPassword();
    }

    @Override
    protected Account authenticate(LoginCommand loginCommand) {
        UsernameLoginCommand usernameLoginDTO = (UsernameLoginCommand) loginCommand;
        String username = usernameLoginDTO.getUsername();
        String password = usernameLoginDTO.getPassword();

        Account account = accountRepository.findByUsername(new AccountName(username));
        if (account == null) {
            throw new BusinessException(LoginErrorCode.B_USER_NOT_EXIST);
        }
        if (!account.getPassword().sameValueAs(new AccountPassword(password))) {
            throw new BusinessException(LoginErrorCode.B_USER_PASSWORD_ERROR);
        }

        return account;
    }

    @Override
    public boolean supports(LoginType loginType) {
        return loginType.equals(LoginType.USERNAME);
    }
}
