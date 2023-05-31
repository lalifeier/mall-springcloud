package com.github.lalifeier.mall.common.error.system;



import com.github.lalifeier.mall.common.error.api.ErrorCode;
import lombok.Getter;
import com.github.lalifeier.mall.common.error.manager.ErrorManager;

@Getter
public enum SystemErrorCodes implements ErrorCode {

    SUCCESS(0, "成功"),
    SYSTEM_ERROR(1, "未知的系统错误");

    private final int nodeNum;
    private final String message;

    SystemErrorCodes(int nodeNum, String message) {
        this.nodeNum = nodeNum;
        this.message = message;
        ErrorManager.register(SystemProjectModule.INSTANCE, this);
    }

}
