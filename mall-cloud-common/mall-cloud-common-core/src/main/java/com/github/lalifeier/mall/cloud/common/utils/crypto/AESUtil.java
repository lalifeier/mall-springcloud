package com.github.lalifeier.mall.cloud.common.utils.crypto;

import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class AESUtil {
  public enum CipherAlgorithm {
    AES_ECB_NoPadding("AES/ECB/NoPadding"), AES_ECB_PKCS5Padding(
        "AES/ECB/PKCS5Padding"), AES_CBC_NoPadding("AES/CBC/NoPadding", 16), AES_CBC_PKCS5Padding(
            "AES/CBC/PKCS5Padding",
            16), AES_CBC_PKCS7Padding("AES/CBC/PKCS7Padding", 16), AES_GCM_NoPadding(
                "AES/GCM/NoPadding", 12), AES_GCM_PKCS5Padding("AES/GCM/PKCS5Padding", 12);

    private final String algorithm;
    private final int blockSize;

    CipherAlgorithm(String algorithm) {
      this.algorithm = algorithm;
      this.blockSize = 0;
    }

    CipherAlgorithm(String algorithm, int blockSize) {
      this.algorithm = algorithm;
      this.blockSize = blockSize;
    }

    public String getAlgorithm() {
      return algorithm;
    }

    public int getBlockSize() {
      return blockSize;
    }
  }

  // 默认加密方式
  public static final String KEY_ALGORITHM = "AES";
  public static final CipherAlgorithm DEFAULT_CIPHER_ALGORITHM =
      CipherAlgorithm.AES_ECB_PKCS5Padding;
  public static final int KEY_SIZE = 128;
  public static final String CHARSET = StandardCharsets.UTF_8.name();

  /**
   * 生成密钥
   *
   * @param keySize 密钥长度
   * @return 密钥对象
   * @throws Exception 如果生成密钥过程出错
   */
  public static SecretKey generateKey(int keySize) throws Exception {
    KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
    keyGenerator.init(keySize);
    return keyGenerator.generateKey();
  }

  public static SecretKey generateKey() throws Exception {
    return generateKey(KEY_SIZE);
  }

  /**
   * 生成密钥字符串
   *
   * @param keySize 密钥长度
   * @return 密钥字符串
   * @throws Exception 如果生成密钥过程出错
   */
  public static String generateKeyString(int keySize) throws Exception {
    SecretKey secretKey = generateKey(keySize);
    byte[] encodedKey = secretKey.getEncoded();
    return Base64.encodeBase64String(encodedKey);
  }

  public static String generateKeyString() throws Exception {
    return generateKeyString(KEY_SIZE);
  }

  private static SecretKey generateSecretKey(String key) {
    byte[] keyBytes = Base64.decodeBase64(key);
    return new SecretKeySpec(keyBytes, KEY_ALGORITHM);
  }

  /**
   * 加密数据
   *
   * @param data 待加密数据
   * @param secretKey 密钥对象
   * @param cipherAlgorithm 加密算法
   * @param iv 初始化向量
   * @return 加密后的数据，使用Base64编码
   * @throws Exception 如果加密过程出错
   */
  public static String encrypt(String data, SecretKey secretKey, CipherAlgorithm cipherAlgorithm,
      byte[] iv) throws Exception {
    Cipher cipher = Cipher.getInstance(cipherAlgorithm.getAlgorithm());
    if (cipherAlgorithm.getBlockSize() > 0) {
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
    } else {
      cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    }
    byte[] encryptedBytes = cipher.doFinal(data.getBytes(CHARSET));
    return Base64.encodeBase64String(encryptedBytes);
  }

  public static String encrypt(String data, String key, CipherAlgorithm cipherAlgorithm, String iv)
      throws Exception {
    SecretKey secretKey = generateSecretKey(key);
    return encrypt(data, secretKey, cipherAlgorithm, iv.getBytes(CHARSET));
  }

  public static String encrypt(String data, String key) throws Exception {
    return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM, "");
  }

  /**
   * 解密数据
   *
   * @param encryptedData 加密数据，使用Base64编码
   * @param secretKey 密钥对象
   * @param cipherAlgorithm 加密算法
   * @param iv 初始化向量
   * @return 解密后的数据
   * @throws Exception 如果解密过程出错
   */
  public static String decrypt(String encryptedData, SecretKey secretKey,
      CipherAlgorithm cipherAlgorithm, byte[] iv) throws Exception {
    byte[] encryptedBytes = Base64.decodeBase64(encryptedData);
    Cipher cipher = Cipher.getInstance(cipherAlgorithm.getAlgorithm());
    if (cipherAlgorithm.getBlockSize() > 0) {
      cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
    } else {
      cipher.init(Cipher.DECRYPT_MODE, secretKey);
    }
    byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
    return new String(decryptedBytes, CHARSET);
  }

  public static String decrypt(String encryptedData, String key, CipherAlgorithm cipherAlgorithm,
      String iv) throws Exception {
    SecretKey secretKey = generateSecretKey(key);
    return decrypt(encryptedData, secretKey, cipherAlgorithm, iv.getBytes(CHARSET));
  }

  public static String decrypt(String encryptedData, String key) throws Exception {
    return decrypt(encryptedData, key, DEFAULT_CIPHER_ALGORITHM, "");
  }

  // public static void main(String[] args) throws Exception {
  // // 生成一个 128 位的 AES 密钥
  // String secretKey = generateKeyString();
  //// String secretKey = Base64Util.encode();
  // System.out.println("SecretKey: " + secretKey);
  //
  // // 加密数据
  // String plaintext = "Hello, world!";
  // String ciphertextString = encrypt(plaintext, secretKey);
  // System.out.println("Ciphertext: " + ciphertextString);
  //
  // // 解密数据
  // String decryptedText = decrypt(ciphertextString, secretKey);
  // System.out.println("Decrypted text: " + decryptedText);
  // }
}
