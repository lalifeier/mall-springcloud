package com.github.lalifeier.mall.cloud.account.infrastructure.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.exception.BusinessException;

public class RegisterException extends BusinessException {

    public RegisterException(ErrorCode errorCode) {
        super(errorCode);
    }

    public RegisterException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public RegisterException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public RegisterException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public RegisterException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }
}
