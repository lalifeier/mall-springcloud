package com.github.lalifeier.mall.cloud.account.domain.account.model.entity;

import java.time.LocalDateTime;

import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountId;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountPassword;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.Email;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.PhoneNumber;
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
  private LocalDateTime createdAt;
  private String createIp;
  private LocalDateTime lastLoginAt;
  private String lastLoginIp;
  private int loginTimes;
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

}
