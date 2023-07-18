package com.github.lalifeier.mall.cloud.common.utils;

import com.github.lalifeier.mall.cloud.common.idgenerator.SnowFlakeIdGenerator;

public class IdGeneratorUtil {
  private static final SnowFlakeIdGenerator INSTANCE = new SnowFlakeIdGenerator(1L, 1L);

  public static Long generateId() {
    return INSTANCE.generateId();
  }
}
