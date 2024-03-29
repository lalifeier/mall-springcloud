package com.github.lalifeier.mall.cloud.account.infrastructure.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.exception.BusinessException;

public class LoginException extends BusinessException {

    public LoginException(ErrorCode errorCode) {
        super(errorCode);
    }

    public LoginException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public LoginException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public LoginException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public LoginException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }
}
