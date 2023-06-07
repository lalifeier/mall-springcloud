package com.github.lalifeier.mall.cloud.account.domain.account.model.entity;

import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.*;
import com.github.lalifeier.mall.cloud.common.model.StatusEnum;
import com.github.lalifeier.mall.cloud.common.model.ddd.Aggregate;
import lombok.Data;

@Data
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
    AccountDO account = new AccountDO();
    account.setUsername(username);
    account.setPassword(password);
    return account;
  }

  public static AccountDO createAccount(Email email, AccountPassword password) {
    AccountDO account = new AccountDO();
    account.setEmail(email);
    account.setPassword(password);
    return account;
  }

  public static AccountDO createAccount(PhoneNumber phone) {
    AccountDO account = new AccountDO();
    account.setPhone(phone);
    return account;
  }
}
