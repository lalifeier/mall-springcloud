package com.github.lalifeier.mall.cloud.common.utils;

import com.github.lalifeier.mall.cloud.common.model.EncryptBody;
import com.github.lalifeier.mall.cloud.common.utils.crypto.AESUtil;
import com.github.lalifeier.mall.cloud.common.utils.crypto.RSAUtil;

public class EncryptBodyUtil {
    public static EncryptBody encrypt(String data, String publicKey) throws Exception {
        String aesKey = AESUtil.generateKeyString();
        String encryptData = AESUtil.encrypt(data, aesKey);
        String key = RSAUtil.encrypt(aesKey, publicKey);
        return new EncryptBody(encryptData, key);
    }

    public static String decrypt(String encryptData, String key, String privateKey) throws Exception {
        String aesKey = RSAUtil.decrypt(key, privateKey);
        return AESUtil.decrypt(encryptData, aesKey);
    }
}
