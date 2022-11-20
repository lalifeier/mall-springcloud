package com.github.lalifeier.mall.auth.infrastructure.error;


import com.github.lalifeier.api.IError;
import com.github.lalifeier.manager.ErrorManager;
import lombok.Getter;

@Getter
public enum LoginError implements IError {
    /**
     * 用户不存在
     */
    USER_NOT_EXIST(0, "用户名不存在"),

    /**
     * 密码错误
     */
    PASSWORD_ERROR(1, "密码错误");

    private final int nodeNum;
    private final String message;

    LoginError(int nodeNum, String message) {
        this.nodeNum = nodeNum;
        this.message = message;
        ErrorManager.register(UserProjectCodes.USER, this);
    }
}

