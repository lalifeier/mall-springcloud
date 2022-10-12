package com.github.lalifeier.exception;


import com.github.lalifeier.api.IError;
import com.github.lalifeier.api.ProjectModule;
import com.github.lalifeier.manager.ErrorInfo;

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

    protected BaseException(IError IError) {
        this(ErrorInfo.parse(IError));
        ProjectModule.check(projectModule(), IError.projectModule());
    }

    protected BaseException(IError IError, Object... args) {
        this(ErrorInfo.parse(IError, args));
        ProjectModule.check(projectModule(), IError.projectModule());
    }

    @Override
    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

}
