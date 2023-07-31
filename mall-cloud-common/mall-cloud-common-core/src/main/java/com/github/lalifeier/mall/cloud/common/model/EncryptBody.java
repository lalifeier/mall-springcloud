package com.github.lalifeier.mall.cloud.common.model;

public class EncryptBody {
    private final String data;
    private final String key;

    public EncryptBody(String data, String key) {
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
