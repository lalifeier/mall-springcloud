package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.service.impl;

import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.dto.login.LoginDTO;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.dto.login.LoginRespDTO;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.provider.login.LoginProvider;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.service.AuthenticationApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationApplicationServiceImpl implements AuthenticationApplicationService {
  private final List<LoginProvider> providers;

  @Override
  public LoginRespDTO login(LoginDTO loginDTO) {
    return providers.stream()
      .filter(provider -> provider.supports(loginDTO.getLoginType()))
      .findFirst()
      .map(provider -> provider.login(loginDTO))
      .orElseThrow(() -> new IllegalArgumentException("Unsupported login method :" + loginDTO.getClass().getName()));
  }
}
