package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.system.SystemErrorCode;

public class BadRequestException extends BaseException {

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(int code, String message) {
        super(code, message);
    }

    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    public ErrorCode defaultErrorCode() {
        return SystemErrorCode.BAD_REQUEST;
    }
}
