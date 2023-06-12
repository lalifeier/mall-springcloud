package com.github.lalifeier.mall.cloud.encryptbody.util;

import com.github.lalifeier.mall.cloud.common.utils.AESUtil;
import com.github.lalifeier.mall.cloud.common.utils.RSAUtil;
import com.github.lalifeier.mall.cloud.encryptbody.model.EncryptData;

public class EncryptUtil {
  public static EncryptData encrypt(String data, String publicKey) throws Exception {
    String aesKey = AESUtil.generateKeyString();
    String encryptData = AESUtil.encrypt(data, aesKey);
    String key = RSAUtil.encrypt(aesKey, publicKey);
    return new EncryptData(encryptData, key);
  }

  public static String decrypt(String encryptData, String key, String privateKey) throws Exception {
    String aesKey = RSAUtil.decrypt(key, privateKey);
    return AESUtil.decrypt(encryptData, aesKey);
  }
}
