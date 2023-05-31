package com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.account.domain.account.repository;

import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.account.domain.account.model.entity.AccountDO;
import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.account.domain.account.model.valueobject.AccountId;
import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.cloud.common.model.ddd.Repository;

public interface AccountRepository extends Repository<AccountDO, AccountId> {
  AccountDO find(AccountName name);
  //Account find(Email email);
  //Account find(PhoneNumber phone);
}
