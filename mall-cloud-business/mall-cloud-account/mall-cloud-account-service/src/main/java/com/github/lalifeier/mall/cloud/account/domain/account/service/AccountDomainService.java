package com.github.lalifeier.mall.cloud.account.domain.account.service;

import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountId;

public interface AccountDomainService {
  Account create(Account account);

  Account update(Account account);

  Account get(AccountId accountId);

  void delete(AccountId accountId);
}
