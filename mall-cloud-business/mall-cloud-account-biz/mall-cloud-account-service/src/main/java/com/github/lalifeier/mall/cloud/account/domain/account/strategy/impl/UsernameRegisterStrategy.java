package com.github.lalifeier.mall.cloud.account.domain.account.strategy.impl;

import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.AccountDO;
import com.github.lalifeier.mall.cloud.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.cloud.account.domain.account.strategy.RegisterStrategy;

import javax.annotation.Resource;

public class UsernameRegisterStrategy implements RegisterStrategy {

  @Resource
  private AccountRepository accountRepository;

  @Override
  public AccountDO register(AccountDO accountDO) {
    //AccountDO account = accountRepository.findByUsername(accountDO.getUsername());
    return null;
  }
}
