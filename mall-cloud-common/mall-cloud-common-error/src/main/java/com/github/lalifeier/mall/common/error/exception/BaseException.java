package com.github.lalifeier.mall.common.error.exception;


import com.github.lalifeier.mall.common.error.api.ErrorCode;
import com.github.lalifeier.mall.common.error.api.ProjectModule;
import com.github.lalifeier.mall.common.error.manager.ErrorInfo;

public abstract class BaseException extends RuntimeException implements IErrorCodeException {
    final ErrorInfo errorInfo;

    protected BaseException(String message) {
        super(message);
        this.errorInfo = ErrorInfo.parse(message);
    }

    protected BaseException(String message, Throwable cause) {
        super(message, cause);
        this.errorInfo = ErrorInfo.parse(message);
    }

    protected BaseException(Throwable cause) {
        super(cause);
        this.errorInfo = ErrorInfo.parse(cause.getMessage());
    }

    protected BaseException(ErrorInfo errorInfo) {
        super(errorInfo.toString());
        this.errorInfo = errorInfo;
    }

    protected BaseException(ErrorCode ErrorCode) {
        this(ErrorInfo.parse(ErrorCode));
        ProjectModule.check(projectModule(), ErrorCode.projectModule());
    }

    protected BaseException(ErrorCode ErrorCode, Object... args) {
        this(ErrorInfo.parse(ErrorCode, args));
        ProjectModule.check(projectModule(), ErrorCode.projectModule());
    }

    @Override
    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

}
