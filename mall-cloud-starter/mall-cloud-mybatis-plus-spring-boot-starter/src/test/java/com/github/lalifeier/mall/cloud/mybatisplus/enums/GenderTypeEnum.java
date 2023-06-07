package com.github.lalifeier.mall.cloud.mybatisplus.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GenderTypeEnum implements IEnum<Integer> {

  WOMAN(0, "女"),

  MAN(1, "男"),

  PRIVACY(2, "保密");

  private final Integer value;

  private final String label;
}

