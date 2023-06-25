package com.github.lalifeier.mall.cloud.account.domain.account.model.entity;

import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.*;
import com.github.lalifeier.mall.cloud.common.enums.StatusEnum;
import com.github.lalifeier.mall.cloud.common.model.ddd.Aggregate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDO implements Aggregate<AccountId> {
  private AccountId id;
  private Email email;
  private PhoneNumber phone;
  private AccountName username;
  private AccountPassword password;
  private StatusEnum status;

  @Override
  public AccountId getId() {
    return id;
  }

  public static AccountDO createAccount(AccountName username, AccountPassword password) {
    return AccountDO.builder().
      username(username).
      password(password).
      build();
  }

  public static AccountDO createAccount(Email email, AccountPassword password) {
    return AccountDO.builder().
      email(email).
      password(password).
      build();
  }

  public static AccountDO createAccount(PhoneNumber phone) {
    return AccountDO.builder().
      phone(phone).
      build();
  }
}
