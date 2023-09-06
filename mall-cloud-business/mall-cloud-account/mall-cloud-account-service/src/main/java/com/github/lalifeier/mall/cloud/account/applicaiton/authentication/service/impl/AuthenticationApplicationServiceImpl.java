package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.service.impl;

import com.github.lalifeier.mall.cloud.account.api.authentication.model.command.RegisterCommand;
import com.github.lalifeier.mall.cloud.account.api.authentication.model.dto.LoginDTO;
import com.github.lalifeier.mall.cloud.account.api.authentication.model.dto.RegisterDTO;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.assembler.AuthenticationAssembler;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.LoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.provider.LoginProvider;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.service.AuthenticationApplicationService;
import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.Email;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.PhoneNumber;
import com.github.lalifeier.mall.cloud.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginErrorCodeEnum;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.RegisterErrorCodeEnum;
import com.github.lalifeier.mall.cloud.account.infrastructure.exception.LoginException;
import com.github.lalifeier.mall.cloud.account.infrastructure.exception.RegisterException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationApplicationServiceImpl implements AuthenticationApplicationService {
    private final List<LoginProvider> loginProviders;
    private final AccountRepository accountRepository;

    private final AuthenticationAssembler authenticationAssembler = AuthenticationAssembler.INSTANCE;

    @Override
    public RegisterDTO register(RegisterCommand command) {
        Long count = accountRepository.countByUserNameOrEmailOrPhone(
                new AccountName(command.getUsername()),
                new Email(command.getEmail()),
                new PhoneNumber(command.getPhone()));
        if (count > 0) {
            throw new RegisterException(RegisterErrorCodeEnum.B_USER_EXIST);
        }

        Account account = authenticationAssembler.toEntity(command);

        accountRepository.save(account);

        return authenticationAssembler.toRegisterDTO(account);
    }

    @Override
    public LoginDTO login(LoginCommand command) {
        return loginProviders.stream()
                .filter(provider -> provider.supports(command.getLoginType()))
                .findFirst()
                .map(provider -> provider.login(command))
                .orElseThrow(() -> new LoginException(LoginErrorCodeEnum.B_LOGIN_TYPE_NOT_SUPPORT));
    }
}
