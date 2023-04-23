package com.github.lalifeier.mall.account.domain.account.model.entity;

import java.time.LocalDateTime;

import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountId;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.Email;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.Password;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.Phone;
import com.github.lalifeier.mall.common.model.Aggregate;
import com.github.lalifeier.mall.common.model.StatusEnum;

import lombok.Data;

@Data
public class Account implements Aggregate<AccountId> {
  private AccountId id;
  private Email email;
  private Phone phone;
  private AccountName username;
  private Password password;
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
}
