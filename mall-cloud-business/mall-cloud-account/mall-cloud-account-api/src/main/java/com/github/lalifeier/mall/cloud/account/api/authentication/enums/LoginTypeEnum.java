package com.github.lalifeier.mall.cloud.account.api.authentication.enums;

import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LoginTypeEnum implements BaseEnum<LoginTypeEnum, String> {
    USERNAME("username", "用户名登陆"),
    EMAIL("email", "邮箱登陆"),
    PHONE("phone", "手机号登陆");

    private final String code;

    private final String description;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
