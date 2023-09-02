package com.github.lalifeier.mall.cloud.account.domain.account.repository;

import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountId;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.Email;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.PhoneNumber;
import com.github.lalifeier.mall.cloud.common.model.marker.Repository;

public interface AccountRepository extends Repository<Account, AccountId> {
  Account findByUsername(AccountName name);

  Account findByEmail(Email email);

  Account findByPhone(PhoneNumber phone);

  Long countByUserNameOrEmailOrPhone(AccountName name, Email email, PhoneNumber phone);
}
