package com.github.lalifeier.mall.auth.error;


import com.github.lalifeier.api.ErrorCode;
import com.github.lalifeier.manager.ErrorManager;
import lombok.Getter;

@Getter
public enum LoginErrorCodes implements ErrorCode {
    /**
     * 用户不存在
     */
    USER_NOT_EXIST(0, "用户名不存在"),
    /**
     * 密码错误
     */
    PASSWORD_ERROR(1, "密码错误");

    private final int nodeNum;
    private final String msg;

    LoginErrorCodes(int nodeNum, String msg) {
        this.nodeNum = nodeNum;
        this.msg = msg;
        ErrorManager.register(UserProjectCodes.USER, this);
    }
}

