package com.github.lalifeier.mall.cloud.common.utils.crypto;

import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class DESUtil {
    public enum CipherAlgorithm {
        DES_ECB_PKCS5Padding("DES/ECB/PKCS5Padding"),
        DES_CBC_PKCS5Padding("DES/CBC/PKCS5Padding"),
        DESede_ECB_PKCS5Padding("DESede/ECB/PKCS5Padding"),
        DESede_CBC_PKCS5Padding("DESede/CBC/PKCS5Padding");

        private final String algorithm;

        CipherAlgorithm(String algorithm) {
            this.algorithm = algorithm;
        }

        public String getAlgorithm() {
            return algorithm;
        }
    }

    public static final String KEY_ALGORITHM = "DES";

    // 默认加密方式
    public static final CipherAlgorithm DEFAULT_CIPHER_ALGORITHM =
            CipherAlgorithm.DES_ECB_PKCS5Padding;

    public static final int KEY_SIZE = 56;

    public static final String CHARSET = StandardCharsets.UTF_8.name();

    /**
     * 生成DES密钥
     *
     * @param keySize 密钥长度，可以是56或112或168
     * @return 密钥对象
     * @throws Exception 如果生成过程出错
     */
    public static SecretKey generateKey(int keySize) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(keySize);
        return keyGenerator.generateKey();
    }

    private static SecretKey generateSecretKey(String key) {
        byte[] keyBytes = Base64.decodeBase64(key);
        return new SecretKeySpec(keyBytes, KEY_ALGORITHM);
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

    /**
     * 加密数据
     *
     * @param data 待加密数据
     * @param key 密钥对象
     * @param cipherAlgorithm
     *     加密算法枚举类型，可以是DES_ECB_PKCS5Padding、DES_CBC_PKCS5Padding、DESede_ECB_PKCS5Padding或DESede_CBC_PKCS5Padding
     * @param iv 初始化向量，只有在使用CBC算法时才需要，可以为null
     * @return 加密后的数据，使用Base64编码
     * @throws Exception 如果加密过程出错
     */
    public static String encrypt(
            String data, SecretKey key, CipherAlgorithm cipherAlgorithm, byte[] iv)
            throws Exception {
        Cipher cipher = Cipher.getInstance(cipherAlgorithm.getAlgorithm());
        if (cipherAlgorithm.getAlgorithm().contains("CBC")) {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        }
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.encodeBase64String(encryptedBytes);
    }

    public static String encrypt(
            String data, String key, CipherAlgorithm cipherAlgorithm, String iv) throws Exception {
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
     * @param key 密钥对象
     * @param cipherAlgorithm
     *     加密算法枚举类型，可以是DES_ECB_PKCS5Padding、DES_CBC_PKCS5Padding、DESede_ECB_PKCS5Padding或DESede_CBC_PKCS5Padding
     * @param iv 初始化向量，只有在使用CBC算法时才需要
     * @return 解密后的数据
     * @throws Exception 如果解密过程出错
     */
    public static String decrypt(
            String encryptedData, SecretKey key, CipherAlgorithm cipherAlgorithm, byte[] iv)
            throws Exception {
        Cipher cipher = Cipher.getInstance(cipherAlgorithm.getAlgorithm());
        if (cipherAlgorithm.getAlgorithm().contains("CBC")) {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, key);
        }
        byte[] encryptedBytes = Base64.decodeBase64(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    public static String decrypt(
            String encryptedData, String key, CipherAlgorithm cipherAlgorithm, String iv)
            throws Exception {
        SecretKey secretKey = generateSecretKey(key);
        return decrypt(encryptedData, secretKey, cipherAlgorithm, iv.getBytes(CHARSET));
    }

    public static String decrypt(String encryptedData, String key) throws Exception {
        return decrypt(encryptedData, key, DEFAULT_CIPHER_ALGORITHM, "");
    }

    //  public static void main(String[] args) throws Exception {
    //    // 生成一个 128 位的 AES 密钥
    //    String secretKey = generateKeyString();
    //    System.out.println("SecretKey: " + secretKey);
    //
    //    // 加密数据
    //    String plaintext = "Hello, world!";
    //    String ciphertextString = encrypt(plaintext, secretKey);
    //    System.out.println("Ciphertext: " + ciphertextString);
    //
    //    // 解密数据
    //    String decryptedText = decrypt(ciphertextString, secretKey);
    //    System.out.println("Decrypted text: " + decryptedText);
    //  }
}
