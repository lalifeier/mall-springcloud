package com.github.lalifeier.mall.cloud.account.domain.account.repository;

import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.AccountDO;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountId;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.Email;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.PhoneNumber;
import com.github.lalifeier.mall.cloud.common.model.ddd.Repository;

public interface AccountRepository extends Repository<AccountDO, AccountId> {
  AccountDO findByUsername(AccountName name);

  AccountDO findByEmail(Email email);
  
  AccountDO findByPhone(PhoneNumber phone);
}
