package com.github.lalifeier.mall.common.system;

import com.github.lalifeier.mall.common.api.ErrorCode;
import com.github.lalifeier.mall.common.manager.ErrorManager;
import lombok.Getter;

@Getter
public enum SystemErrorCodes implements ErrorCode {

    SUCCESS(0, "成功"),

//    BIZ_ERROR(1 , "通用的业务逻辑错误"),
    SYSTEM_ERROR(1, "未知的系统错误");

    private final int nodeNum;
    private final String msg;

    SystemErrorCodes(int nodeNum, String msg) {
        this.nodeNum = nodeNum;
        this.msg = msg;
        ErrorManager.register(SystemProjectModule.INSTANCE, this);
    }

}
