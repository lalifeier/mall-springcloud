package com.github.lalifeier.mall.cloud.common.exception;



import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.manager.ErrorInfo;
import lombok.Getter;

@Getter
public abstract class BaseRuntimeException extends RuntimeException implements IErrorCodeException {

    final ErrorInfo errorInfo;

    protected BaseRuntimeException(String message) {
        super(message);
        this.errorInfo = ErrorInfo.parse(message);
    }

    protected BaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.errorInfo = ErrorInfo.parse(message);
    }

    protected BaseRuntimeException(Throwable cause) {
        super(cause);
        this.errorInfo = ErrorInfo.parse(cause.getMessage());
    }

    protected BaseRuntimeException(ErrorInfo errorInfo) {
        super(errorInfo.toString());
        this.errorInfo = errorInfo;
    }

    protected BaseRuntimeException(ErrorCode ErrorCode) {
        this(ErrorInfo.parse(ErrorCode));
        ProjectModule.check(projectModule(), ErrorCode.projectModule());
    }

    protected BaseRuntimeException(ErrorCode ErrorCode, Object... args) {
        this(ErrorInfo.parse(ErrorCode, args));
        ProjectModule.check(projectModule(), ErrorCode.projectModule());
    }

    @Override
    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }
}

