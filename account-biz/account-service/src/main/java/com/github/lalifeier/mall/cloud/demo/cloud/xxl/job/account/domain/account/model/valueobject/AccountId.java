package com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.account.domain.account.model.valueobject;

import com.github.lalifeier.mall.cloud.common.model.ddd.Identifier;
import lombok.Getter;

import javax.validation.ValidationException;

@Getter
public class AccountId implements Identifier<Long> {
  private final Long value;

  public AccountId(final Long value) {
    if (value == null) {
      throw new ValidationException("账号id不能为空");
    }
    this.value = value;
  }
}
