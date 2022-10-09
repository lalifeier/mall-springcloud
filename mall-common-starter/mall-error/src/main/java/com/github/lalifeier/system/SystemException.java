package com.github.lalifeier.system;


import com.github.lalifeier.api.ErrorCode;
import com.github.lalifeier.api.ProjectModule;
import com.github.lalifeier.exception.BaseException;
import com.github.lalifeier.manager.ErrorInfo;

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
