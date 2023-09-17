package com.github.lalifeier.mall.cloud.account.domain.account.model.entity;

import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountId;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountPassword;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.Email;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.PhoneNumber;
import com.github.lalifeier.mall.cloud.common.enums.StatusEnum;
import com.github.lalifeier.mall.cloud.common.model.marker.Aggregate;

import lombok.Data;

@Data
public class Account implements Aggregate<AccountId> {
  private AccountId id;
  private AccountName username;
  private AccountPassword password;
  private Email email;
  private PhoneNumber phone;
  private StatusEnum status;

  @Override
  public AccountId getId() {
    return id;
  }
}
