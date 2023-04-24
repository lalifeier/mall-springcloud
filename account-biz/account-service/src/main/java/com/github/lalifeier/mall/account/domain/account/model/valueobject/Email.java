package com.github.lalifeier.mall.account.domain.account.model.valueobject;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.lalifeier.mall.common.model.ValueObject;
import lombok.Getter;
import org.apache.commons.lang3.Validate;

import javax.validation.ValidationException;
import java.util.regex.Pattern;

@Getter
public class Email implements ValueObject<Email> {
  private final String email;

  private static final Pattern VALID_PATTERN = Pattern
    .compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");

  public Email(final String email) {
    if (StringUtils.isEmpty(email)) {
      throw new ValidationException("邮箱不能为空");
    }

    Validate.isTrue(VALID_PATTERN.matcher(email).matches(),
      "邮箱格式不正确");

    this.email = email;
  }

  @Override
  public boolean sameValueAs(Email other) {
    return other != null && this.email.equals(other.email);
  }

}
