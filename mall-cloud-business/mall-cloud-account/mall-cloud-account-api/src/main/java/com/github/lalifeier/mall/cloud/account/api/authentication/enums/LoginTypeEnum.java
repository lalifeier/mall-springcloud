package com.github.lalifeier.mall.cloud.account.api.authentication.enums;

import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LoginTypeEnum implements BaseEnum<LoginTypeEnum, String> {
    USERNAME("username"),
    EMAIL("email"),
    PHONE("phone");
    private final String code;

    @Override
    public String getCode() {
        return code;
    }
}
