package com.github.lalifeier.mall.common.error.system;


import com.github.lalifeier.mall.common.error.api.ErrorCode;
import com.github.lalifeier.mall.common.error.api.ProjectModule;
import com.github.lalifeier.mall.common.error.manager.ErrorInfo;
import com.github.lalifeier.mall.common.error.exception.BaseException;

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

    public SystemException(ErrorCode ErrorCode) {
        super(ErrorCode);
    }

    public SystemException(ErrorCode ErrorCode, Object... args) {
        super(ErrorCode, args);
    }

    @Override
    public ProjectModule projectModule() {
        return SystemProjectModule.INSTANCE;
    }
}
