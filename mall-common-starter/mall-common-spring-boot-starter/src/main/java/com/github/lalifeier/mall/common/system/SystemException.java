package com.github.lalifeier.mall.common.system;

import com.github.lalifeier.mall.common.api.ErrorCode;
import com.github.lalifeier.mall.common.api.ProjectModule;
import com.github.lalifeier.mall.common.exception.BaseException;
import com.github.lalifeier.mall.common.manager.ErrorInfo;

public class SystemException extends BaseException {


    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(ErrorInfo errorInfo) {
        super(errorInfo);
    }

    public SystemException(ErrorCode errorCode) {
        super(errorCode);
    }

    public SystemException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }

    @Override
    public ProjectModule projectModule() {
        return SystemProjectModule.INSTANCE;
    }
}