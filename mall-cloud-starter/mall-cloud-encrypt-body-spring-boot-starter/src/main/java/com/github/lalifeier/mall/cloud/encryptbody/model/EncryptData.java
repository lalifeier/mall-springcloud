package com.github.lalifeier.mall.cloud.encryptbody.model;

public class EncryptData {
  private final String data;
  private final String key;

  public EncryptData(String data, String key) {
    this.data = data;
    this.key = key;
  }

  public String getData() {
    return data;
  }

  public String getKey() {
    return key;
  }
}
