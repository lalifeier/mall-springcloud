package com.github.lalifeier.mall.cloud.account.domain.account.strategy;

import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.AccountDO;

public interface RegisterStrategy {
  public AccountDO register(AccountDO accountDO);
}
