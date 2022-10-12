package com.github.lalifeier.system;


import com.github.lalifeier.api.IError;
import com.github.lalifeier.manager.ErrorManager;
import lombok.Getter;

@Getter
public enum SystemError implements IError {

    SUCCESS(0, "成功"),
    SYSTEM_ERROR(1, "未知的系统错误");

    private final int nodeNum;
    private final String message;

    SystemError(int nodeNum, String message) {
        this.nodeNum = nodeNum;
        this.message = message;
        ErrorManager.register(SystemProjectModule.INSTANCE, this);
    }

}
