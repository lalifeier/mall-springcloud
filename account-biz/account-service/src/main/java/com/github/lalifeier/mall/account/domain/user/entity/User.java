package com.github.lalifeier.mall.account.domain.user.entity;

import com.github.lalifeier.mall.account.domain.account.model.entity.Account;

public class User {
  private Long id;
  private Long accountId;
  private String name;
  // private Gender gender;
    // private List<Role> roles;

  public void linkAccount(Account account) {
      this.accountId = account.getId();
  }

  public void updateProfile(String name ) {
      // 更新用户信息
      this.name = name;
      // this.gender = gender;
  }

    // public void addRole(Role role) {
  // if (roles == null) {
  // roles = new ArrayList<>();
  // }
  // roles.add(role);
  // }
}
