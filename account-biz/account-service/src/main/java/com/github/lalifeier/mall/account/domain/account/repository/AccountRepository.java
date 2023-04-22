package com.github.lalifeier.mall.account.domain.account.repository;

import com.github.lalifeier.mall.account.domain.account.model.entity.AccountDO;

public interface AccountRepository {
  Long save(AccountDO account);

  AccountDO findByUsername(String username);
}
