package com.github.lalifeier.mall.account.domain.account.model.valueobject;

import com.github.lalifeier.mall.common.model.Identifier;
import lombok.Getter;

import javax.validation.ValidationException;

@Getter
public class AccountId implements Identifier<Long> {
  private final Long id;

  public AccountId(final Long id) {
    if (id == null) {
      throw new ValidationException("账号id不能为空");
    }
    this.id = id;
  }

  @Override
  public Long getValue() {
    return id;
  }
}
