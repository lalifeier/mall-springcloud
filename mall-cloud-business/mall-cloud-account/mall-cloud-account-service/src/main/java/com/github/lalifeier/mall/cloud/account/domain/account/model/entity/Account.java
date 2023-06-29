package com.github.lalifeier.mall.cloud.account.domain.account.model.entity;

import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.*;
import com.github.lalifeier.mall.cloud.common.enums.StatusEnum;
import com.github.lalifeier.mall.cloud.common.model.ddd.Aggregate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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

  public static Account createAccount(AccountName username, AccountPassword password) {
    return Account.builder().
      username(username).
      password(password).
      build();
  }

  public static Account createAccount(Email email, AccountPassword password) {
    return Account.builder().
      email(email).
      password(password).
      build();
  }

  public static Account createAccount(PhoneNumber phone) {
    return Account.builder().
      phone(phone).
      build();
  }
}
