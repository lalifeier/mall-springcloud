package com.github.lalifeier.mall.account.domain.account.model.valueobject;

import com.github.lalifeier.mall.common.model.Identifier;

import lombok.Getter;

@Getter
public class AccountId implements Identifier<Long> {
  private Long id;

  public AccountId(final Long id) {
    if (id == null) {
      throw new IllegalArgumentException("账号id不能为空");
    }
    this.id = id;
  }

  @Override
  public Long getValue() {
    return id;
  }

  // @Override
  // public boolean sameValueAs(AccountId other) {
  // return other != null && this.id.equals(other.id);
  // }
}
