package com.github.lalifeier.mall.common.model;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
  ENABLED("启用", "1"),
  DISABLED("禁用", "0");

  private String label;

  private String value;

  public static String getLabelByValue(String value) {
    if (StringUtils.isBlank(value)) {
      return "";
    }
    for (StatusEnum s : StatusEnum.values()) {
      if (value.equals(s.getValue())) {
        return s.getLabel();
      }
    }
    return "";
  }

  public static StatusEnum getStatusEnum(String value) {
    if (StringUtils.isBlank(value)) {
      return null;
    }
    for (StatusEnum s : StatusEnum.values()) {
      if (value.equals(s.getValue())) {
        return s;
      }
    }
    return null;
  }

}
