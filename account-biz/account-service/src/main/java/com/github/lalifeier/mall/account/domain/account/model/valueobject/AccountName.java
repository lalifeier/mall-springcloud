package com.github.lalifeier.mall.account.domain.account.model.valueobject;

import org.apache.commons.lang3.StringUtils;

import com.github.lalifeier.mall.common.model.ValueObject;

import lombok.Getter;

@Getter
public class AccountName implements ValueObject<AccountName> {
  private String name;

  public AccountName(String name) {
    if (StringUtils.isEmpty(name)) {
      throw new IllegalArgumentException("账号名不能为空");
    }
    this.name = name;
  }

  @Override
  public boolean sameValueAs(AccountName other) {
    return other != null && this.name.equals(other.name);
  }
}
