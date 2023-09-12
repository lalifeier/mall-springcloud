package com.github.lalifeier.mall.cloud.common.utils.codec;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {
    /**
     * 将字符串编码为Base64格式
     *
     * @param data 待编码字符串
     * @return Base64编码字符串
     */
    public static String encode(String data) {
        byte[] bytes = data.getBytes();
        byte[] encodedBytes = Base64.encodeBase64(bytes);
        return new String(encodedBytes);
    }

    /**
     * 将Base64格式的字符串解码为普通字符串
     *
     * @param data Base64编码字符串
     * @return 解码后的字符串
     */
    public static String decode(String data) {
        byte[] decodedBytes = Base64.decodeBase64(data.getBytes());
        return new String(decodedBytes);
    }
}
