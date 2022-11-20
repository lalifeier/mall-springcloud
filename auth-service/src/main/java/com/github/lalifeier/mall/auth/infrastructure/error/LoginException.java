package com.github.lalifeier.mall.auth.infrastructure.error;


import com.github.lalifeier.api.IError;
import com.github.lalifeier.api.ProjectModule;
import com.github.lalifeier.exception.BaseException;
import com.github.lalifeier.manager.ErrorInfo;

public class LoginException extends BaseException {

    protected LoginException(String message) {
        super(message);
    }

    protected LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    protected LoginException(Throwable cause) {
        super(cause);
    }

    protected LoginException(ErrorInfo errorInfo) {
        super(errorInfo);
    }

    protected LoginException(IError IError) {
        super(IError);
    }

    protected LoginException(IError IError, Object... args) {
        super(IError, args);
    }

    @Override
    public ProjectModule projectModule() {
        return UserProjectCodes.LOGIN;
    }
}
