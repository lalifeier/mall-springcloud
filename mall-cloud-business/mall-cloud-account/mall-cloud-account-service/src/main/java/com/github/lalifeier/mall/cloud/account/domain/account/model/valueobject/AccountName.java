package com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject;

import com.github.lalifeier.mall.cloud.common.model.ddd.ValueObject;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ValidationException;

@Getter
public class AccountName implements ValueObject<AccountName> {
  private final String value;

  public AccountName(String value) {
    if (StringUtils.isEmpty(value)) {
      throw new ValidationException("账号名不能为空");
    }
    this.value = value;
  }

  @Override
  public boolean sameValueAs(AccountName other) {
    return other != null && this.value.equals(other.value);
  }
}