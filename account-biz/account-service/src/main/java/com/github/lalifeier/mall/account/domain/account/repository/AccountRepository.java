package com.github.lalifeier.mall.account.domain.account.repository;

import com.github.lalifeier.mall.account.domain.account.model.entity.Account;

public interface AccountRepository {
  Long save(Account account);

  Account findByUsername(String username);
}
