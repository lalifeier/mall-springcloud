package com.github.lalifeier.mall.account.domain.account.model.entity;

import com.github.lalifeier.mall.account.domain.account.model.valueobject.*;
import com.github.lalifeier.mall.common.model.Aggregate;
import com.github.lalifeier.mall.common.model.StatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Account implements Aggregate<AccountId> {
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

}
