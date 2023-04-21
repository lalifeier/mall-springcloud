package com.github.lalifeier.mall.account.domain.account.model.entity;

import lombok.Data;

@Data
public class Account {
  private Long id;
  private String username;
  private String password;
  private String email;


  public void register(String username, String password, String email) {
    // 检查用户名、密码、邮箱是否符合规范
    // 如果符合，则注册帐户
    this.username = username;
    this.password = password;
    this.email = email;
  }


}
