package com.github.lalifeier.mall.cloud.account.domain.account.service;

import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.AccountDO;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginType;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.RegisterType;

public interface AccountDomainService {
  public AccountDO login(LoginType type, AccountDO accountDO);

  public AccountDO register(RegisterType type, AccountDO accountDO);
}
