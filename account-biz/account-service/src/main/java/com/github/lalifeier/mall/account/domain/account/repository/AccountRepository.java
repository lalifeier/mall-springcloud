package com.github.lalifeier.mall.account.domain.account.repository;

import com.github.lalifeier.mall.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountId;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.Email;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.PhoneNumber;
import com.github.lalifeier.mall.common.model.Repository;

public interface AccountRepository extends Repository<Account, AccountId> {
  Account find(AccountName name);
  //Account find(Email email);
  //Account find(PhoneNumber phone);
}
