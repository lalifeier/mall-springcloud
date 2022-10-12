package com.github.lalifeier.exception;


import com.github.lalifeier.api.IError;
import com.github.lalifeier.api.ProjectModule;
import com.github.lalifeier.manager.ErrorInfo;
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

    protected BaseRuntimeException(IError IError) {
        this(ErrorInfo.parse(IError));
        ProjectModule.check(projectModule(), IError.projectModule());
    }

    protected BaseRuntimeException(IError IError, Object... args) {
        this(ErrorInfo.parse(IError, args));
        ProjectModule.check(projectModule(), IError.projectModule());
    }

    @Override
    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }
}

