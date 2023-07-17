package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.service.impl;

import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.LoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.dto.LoginDTO;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.provider.login.LoginProvider;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.service.AuthenticationApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationApplicationServiceImpl implements AuthenticationApplicationService {
  private final List<LoginProvider> loginProviders;

  public AuthenticationApplicationServiceImpl(List<LoginProvider> loginProviders) {
    this.loginProviders = loginProviders;
  }

  @Override
  public LoginDTO login(LoginCommand loginCommand) {
    return loginProviders.stream()
      .filter(provider -> provider.supports(loginCommand.getLoginType()))
      .findFirst()
      .map(provider -> provider.login(loginCommand))
      .orElseThrow(() -> new IllegalArgumentException("Unsupported login method :" + loginCommand.getClass().getName()));
  }
}
