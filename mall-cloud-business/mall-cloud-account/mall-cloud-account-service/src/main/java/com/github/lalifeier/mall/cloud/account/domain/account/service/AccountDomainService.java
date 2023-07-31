package com.github.lalifeier.mall.cloud.account.domain.account.service;

import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountId;

public interface AccountDomainService {
    void create(Account account);

    void update(Account account);

    Account get(AccountId accountId);

    void delete(AccountId accountId);
}
