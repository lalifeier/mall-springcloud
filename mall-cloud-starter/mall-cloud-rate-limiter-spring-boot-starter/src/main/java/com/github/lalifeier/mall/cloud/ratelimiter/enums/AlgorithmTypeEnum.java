package com.github.lalifeier.mall.cloud.ratelimiter.enums;

public enum AlgorithmTypeEnum {
    TOKEN_BUCKET("tokenBucket");
    private final String name;

    AlgorithmTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
