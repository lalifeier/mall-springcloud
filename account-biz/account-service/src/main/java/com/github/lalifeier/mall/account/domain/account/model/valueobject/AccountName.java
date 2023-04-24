package com.github.lalifeier.mall.account.domain.account.model.valueobject;

import com.github.lalifeier.mall.common.model.ValueObject;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ValidationException;

@Getter
public class AccountName implements ValueObject<AccountName> {
  private final String name;

  public AccountName(String name) {
    if (StringUtils.isEmpty(name)) {
      throw new ValidationException("账号名不能为空");
    }
    this.name = name;
  }

  @Override
  public boolean sameValueAs(AccountName other) {
    return other != null && this.name.equals(other.name);
  }
}
