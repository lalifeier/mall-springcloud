package com.github.lalifeier.mall.cloud.common.utils.crypto;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;

public class RSAUtil {

    public enum CipherAlgorithm {
        RSA("RSA"),
        RSA_ECB_PKCS1Padding("RSA/ECB/PKCS1Padding"),
        RSA_ECB_OAEPWithSHA_1AndMGF1Padding("RSA/ECB/OAEPWithSHA-1AndMGF1Padding"),
        RSA_ECB_OAEPWithSHA_256AndMGF1Padding("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");

        private final String algorithm;

        CipherAlgorithm(String algorithm) {
            this.algorithm = algorithm;
        }

        public String getAlgorithm() {
            return algorithm;
        }
    }

    public static final String KEY_ALGORITHM = "RSA";

    // 默认加密方式
    public static final CipherAlgorithm DEFAULT_CIPHER_ALGORITHM =
            CipherAlgorithm.RSA_ECB_PKCS1Padding;

    public static final int KEY_SIZE = 2048;

    public static final String CHARSET = StandardCharsets.UTF_8.name();

    /**
     * 生成RSA密钥对
     *
     * @param keySize 密钥长度
     * @return KeyPair对象
     * @throws NoSuchAlgorithmException 如果未找到指定的算法
     */
    public static KeyPair generateKeyPair(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.generateKeyPair();
    }

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        return generateKeyPair(KEY_SIZE);
    }

    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr 公钥字符串
     * @return 公钥对象
     * @throws InvalidKeySpecException 如果公钥字符串无效
     */
    public static PublicKey loadPublicKey(String publicKeyStr) throws Exception {
        byte[] publicKeyBytes = Base64.decodeBase64(publicKeyStr.getBytes(CHARSET));
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 从字符串中加载私钥
     *
     * @param privateKeyStr 私钥字符串
     * @return 私钥对象
     * @throws InvalidKeySpecException 如果私钥字符串无效
     */
    public static PrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
        byte[] privateKeyBytes = Base64.decodeBase64(privateKeyStr.getBytes(CHARSET));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 使用公钥加密数据
     *
     * @param data 待加密数据
     * @param publicKey 公钥对象
     * @return 加密后的数据，使用Base64编码
     * @throws Exception 如果加密过程出错
     */
    public static String encrypt(String data, PublicKey publicKey, CipherAlgorithm algorithm)
            throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes(CHARSET));
        return Base64.encodeBase64String(encryptedBytes);
    }

    public static String encrypt(String data, String publicKey, CipherAlgorithm algorithm)
            throws Exception {
        PublicKey key = loadPublicKey(publicKey);
        return RSAUtil.encrypt(data, key, algorithm);
    }

    public static String encrypt(String data, String publicKey) throws Exception {
        PublicKey key = loadPublicKey(publicKey);
        return RSAUtil.encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 使用私钥解密数据
     *
     * @param encryptedData 加密数据，使用Base64编码
     * @param privateKey 私钥对象
     * @return 解密后的数据
     * @throws Exception 如果解密过程出错
     */
    public static String decrypt(
            String encryptedData, PrivateKey privateKey, CipherAlgorithm algorithm)
            throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] encryptedBytes = Base64.decodeBase64(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    public static String decrypt(String encryptedData, String privateKey, CipherAlgorithm algorithm)
            throws Exception {
        PrivateKey key = loadPrivateKey(privateKey);
        return RSAUtil.decrypt(encryptedData, key, algorithm);
    }

    public static String decrypt(String encryptedData, String privateKey) throws Exception {
        PrivateKey key = loadPrivateKey(privateKey);
        return RSAUtil.decrypt(encryptedData, key, DEFAULT_CIPHER_ALGORITHM);
    }

    public static String generatePublicKey(KeyPair keyPair) {
        PublicKey publicKey = keyPair.getPublic();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey.getEncoded());
        return Base64.encodeBase64String(keySpec.getEncoded());
    }

    public static String generatePrivateKey(KeyPair keyPair) {
        PrivateKey privateKey = keyPair.getPrivate();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
        return Base64.encodeBase64String(keySpec.getEncoded());
    }

    public static void main(String[] args) throws Exception {
        KeyPair keyPair = generateKeyPair();

        String publicKey = generatePublicKey(keyPair);
        System.out.println("publicKey:" + publicKey);

        String privateKey = generatePrivateKey(keyPair);
        System.out.println("privateKey:" + privateKey);

        String data = "123456";
        String encryptData = encrypt(data, publicKey);
        System.out.println("encryptData: " + encryptData);

        String decryptData = decrypt(encryptData, privateKey);
        System.out.println("decryptData: " + decryptData);
    }
}
