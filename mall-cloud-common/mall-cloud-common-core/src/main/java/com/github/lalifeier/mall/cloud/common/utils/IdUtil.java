package com.github.lalifeier.mall.cloud.common.utils;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.aventrix.jnanoid.jnanoid.NanoIdUtils.DEFAULT_ALPHABET;
import static com.aventrix.jnanoid.jnanoid.NanoIdUtils.DEFAULT_NUMBER_GENERATOR;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdUtil {
    /**
     * 获取随机UUID
     *
     * @return 随机UUID字符串
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID字符串，去掉了横线
     */
    public static String simpleUUID() {
        return randomUUID().replace("-", "");
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 随机UUID字符串
     */
    public static String fastUUID() {
        return UUIDUtil.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 简化的UUID字符串，去掉了横线
     */
    public static String fastSimpleUUID() {
        return fastUUID().replace("-", "");
    }

    /**
     * 获取随机NanoId
     *
     * @return 随机NanoId
     */
    public static String nanoId() {
        return NanoIdUtils.randomNanoId();
    }

    /**
     * 获取随机NanoId
     *
     * @param size ID中的字符数量
     * @return 随机NanoId
     */
    public static String nanoId(int size) {
        return NanoIdUtils.randomNanoId(DEFAULT_NUMBER_GENERATOR, DEFAULT_ALPHABET, size);
    }
}
