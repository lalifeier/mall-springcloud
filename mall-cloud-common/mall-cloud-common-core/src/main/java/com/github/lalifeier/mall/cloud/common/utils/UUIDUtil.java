package com.github.lalifeier.mall.cloud.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UUIDUtil {
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static UUID randomUUID() {
        return UUID.randomUUID();
    }

    /**
     * 返回使用ThreadLocalRandom的UUID，比默认的UUID性能更优
     *
     * @return 随机UUID
     */
    public static UUID fastUUID() {
        return new UUID(random.nextLong(), random.nextLong());
    }
}
