package com.github.lalifeier.mall.sign.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AppCredentialsGenerator {
  private static final int APP_ID_LENGTH = 16;
  private static final int APP_KEY_LENGTH = 32;
  private static final int APP_SECRET_LENGTH = 128;

  public static void main(String[] args) {
    String appId = generateAppId();
    String appKey = generateAppKey();
    String appSecret = generateAppSecret();

    System.out.println("App ID: " + appId);
    System.out.println("App Key: " + appKey);
    System.out.println("App Secret: " + appSecret);
  }

  private static String generateAppId() {
    return generateRandomString(APP_ID_LENGTH);
  }

  private static String generateAppKey() {
    return generateRandomString(APP_KEY_LENGTH);
  }

  private static String generateAppSecret() {
    SecureRandom random = new SecureRandom();
    byte[] bytes = new byte[APP_SECRET_LENGTH];
    random.nextBytes(bytes);
    byte[] hashedBytes = hashBytes(bytes);
    return Base64.getEncoder().encodeToString(hashedBytes);
  }

  private static String generateRandomString(int length) {
    SecureRandom random = new SecureRandom();
    byte[] bytes = new byte[length];
    random.nextBytes(bytes);
    return Base64.getEncoder().encodeToString(bytes);
  }

  private static byte[] hashBytes(byte[] bytes) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      return digest.digest(bytes);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("SHA-512 algorithm not found", e);
    }
  }
}
