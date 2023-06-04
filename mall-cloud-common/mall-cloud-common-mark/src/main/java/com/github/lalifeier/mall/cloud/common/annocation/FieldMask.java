package com.github.lalifeier.mall.cloud.common.annocation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.lalifeier.mall.cloud.common.component.MaskSerialize;
import com.github.lalifeier.mall.cloud.common.enums.MaskEnum;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = MaskSerialize.class)
public @interface FieldMask {

  /**
   * 脱敏类型
   *
   * @return
   */
  MaskEnum value();
}
