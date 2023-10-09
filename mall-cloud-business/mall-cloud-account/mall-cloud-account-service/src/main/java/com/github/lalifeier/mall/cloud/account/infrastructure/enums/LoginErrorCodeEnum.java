package com.github.lalifeier.mall.cloud.account.infrastructure.enums;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import lombok.Getter;

@Getter
public enum LoginErrorCodeEnum implements ErrorCode {
    B_USER_NOT_EXIST(1, "用户名不存在"),

    B_USER_PASSWORD_ERROR(2, "用户名或密码错误"),

    B_LOGIN_NOT_EXIST(3, "登录类型不能为空"),

    B_LOGIN_TYPE_ERROR(4, "登录类型错误"),

    B_LOGIN_TYPE_NOT_SUPPORT(5, "不支持该登录方式"),

    B_USER_PASSWORD_NOT_EXIST(5, "用户名或密码为空");

    private final int nodeNum;
    private final String description;
    private final String message;

    LoginErrorCodeEnum(int nodeNum, String description) {
        this(nodeNum, description, "");
    }

    LoginErrorCodeEnum(int nodeNum, String description, String message) {

        this.nodeNum = nodeNum;
        this.description = description;
        this.message = message;
    }

    @Override
    public ProjectModule getProjectModule() {
        return ProjectModuleEnum.LOGIN;
    }

    @Override
    public String getStatus() {
        return name();
    }
}
