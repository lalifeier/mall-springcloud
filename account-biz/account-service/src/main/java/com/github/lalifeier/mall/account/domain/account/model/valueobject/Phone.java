package com.github.lalifeier.mall.account.domain.account.model.valueobject;

import java.util.regex.Pattern;

import org.apache.commons.lang3.Validate;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.lalifeier.mall.common.model.ValueObject;

import lombok.Getter;

@Getter
public class Phone implements ValueObject<Phone> {
  private String phone;

  private static final Pattern VALID_PATTERN = Pattern
      .compile("^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$");

  public Phone(final String phone) {
    if (StringUtils.isEmpty(phone)) {
      throw new IllegalArgumentException("手机号不能为空");
    }

    Validate.isTrue(VALID_PATTERN.matcher(phone).matches(),
        "手机号格式不正确");

    this.phone = phone;
  }

  @Override
  public boolean sameValueAs(Phone other) {
    return other != null && this.phone.equals(other.phone);
  }
}
