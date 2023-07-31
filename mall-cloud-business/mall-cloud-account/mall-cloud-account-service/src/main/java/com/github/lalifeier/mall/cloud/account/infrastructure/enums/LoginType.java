package com.github.lalifeier.mall.cloud.account.infrastructure.enums;

public enum LoginType {
    USERNAME,
    EMAIL,
    PHONE;

    public static LoginType parse(String loginType) {
        try {
            return LoginType.valueOf(loginType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("不支持此登录方式：" + loginType);
        }
    }
}
