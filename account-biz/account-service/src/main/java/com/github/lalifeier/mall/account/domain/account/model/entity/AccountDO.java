package com.github.lalifeier.mall.account.domain.account.model.entity;

import java.time.LocalDateTime;

import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountStatus;

import lombok.Data;

@Data
public class AccountDO {
  private Long id;
  private String email;
  private String phone;
  private String username;
  private String password;
  private LocalDateTime createdAt;
  private String createIp;
  private LocalDateTime lastLoginAt;
  private String lastLoginIp;
  private int loginTimes;
  private AccountStatus status;

  public AccountDO(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public void changePassword(String oldPassword, String newPassword) {
    if (!password.equals(oldPassword)) {
      throw new IllegalArgumentException("原密码错误");
    }
    this.password = newPassword;
  }

  public boolean verifyPassword(String password) {
    return this.password.equals(password);
  }
}
