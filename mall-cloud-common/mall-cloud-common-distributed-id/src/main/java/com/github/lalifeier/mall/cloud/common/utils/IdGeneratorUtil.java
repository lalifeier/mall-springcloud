package com.github.lalifeier.mall.cloud.common.utils;

import com.github.lalifeier.mall.cloud.common.id.SnowFlakeIdGenerator;

public class IdGeneratorUtil {
    private static final SnowFlakeIdGenerator INSTANCE = new SnowFlakeIdGenerator();

    public static long generateId() {
        return INSTANCE.generateId();
    }

    // public static void main(String[] args) {
    // for (int i = 0; i < 1000; i++) {
    // long id = IdGeneratorUtil.generateId();
    // System.out.println(id);
    // }
    // }
}
