package com.github.lalifeier.mall.cloud.account.domain.account.service.impl;

import com.github.lalifeier.mall.cloud.account.domain.account.factory.RegisterFactory;
import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.AccountDO;
import com.github.lalifeier.mall.cloud.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.cloud.account.domain.account.service.AccountDomainService;
import com.github.lalifeier.mall.cloud.account.domain.account.strategy.RegisterStrategy;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginType;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.RegisterType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountDomainServiceImpl implements AccountDomainService {
  @Resource
  private AccountRepository accountRepository;

  @Override
  public AccountDO login(LoginType type, AccountDO accountDO) {

    return null;
  }

  @Override
  public AccountDO register(RegisterType type, AccountDO accountDO) {
    RegisterStrategy strategy = RegisterFactory.createRegisterStrategy(type);

    return strategy.register(accountDO);
  }
}
