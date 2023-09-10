package com.github.lalifeier.mall.cloud.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
// @JsonSerialize(using = BaseEnumSerializer.class)
public enum StatusEnum implements BaseEnum<StatusEnum, Integer> {
  ENABLED(1, "启用"), DISABLED(0, "禁用");

  private final Integer code;

  private final String description;

  @Override
  public Integer getCode() {
    return code;
  }

  @Override
  public String getDescription() {
    return description;
  }
}
