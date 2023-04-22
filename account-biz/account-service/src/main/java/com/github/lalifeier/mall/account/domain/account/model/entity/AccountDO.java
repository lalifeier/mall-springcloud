package com.github.lalifeier.mall.account.domain.account.model.entity;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    setPassword(password);
  }

  public void setPassword(String password) {
    String salt = BCrypt.gensalt();
    this.password = new BCryptPasswordEncoder().encode(password + salt);
  }

  public boolean verifyPassword(String password) {
    return new BCryptPasswordEncoder().matches(password + getSalt(), getPassword());
  }

  private String getSalt() {
    return getPassword().substring(getPassword().length() - 31);
  }

  public void changePassword(String oldPassword, String newPassword) {
    if (this.verifyPassword(oldPassword)) {
      throw new IllegalArgumentException("原密码错误");
    }

    setPassword(newPassword);
  }
}
