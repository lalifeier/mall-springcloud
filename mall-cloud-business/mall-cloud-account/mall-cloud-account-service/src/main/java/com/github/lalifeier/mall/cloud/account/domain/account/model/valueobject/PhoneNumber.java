package com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.lalifeier.mall.cloud.common.model.marker.ValueObject;
import jakarta.validation.ValidationException;
import lombok.Getter;
import org.apache.commons.lang3.Validate;

import java.util.regex.Pattern;

@Getter
public class PhoneNumber implements ValueObject<PhoneNumber> {
  private final String value;

  private static final Pattern VALID_PATTERN = Pattern
    .compile("^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$");

  public PhoneNumber(final String value) {
    if (StringUtils.isEmpty(value)) {
      throw new ValidationException("手机号不能为空");
    }

    Validate.isTrue(VALID_PATTERN.matcher(value).matches(),
      "手机号格式不正确");

    this.value = value;
  }

  @Override
  public boolean sameValueAs(PhoneNumber other) {
    return other != null && this.value.equals(other.value);
  }
}
