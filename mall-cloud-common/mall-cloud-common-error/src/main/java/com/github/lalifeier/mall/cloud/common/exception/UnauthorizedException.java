package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.system.SystemErrorCode;

public class UnauthorizedException extends BaseException {

    public UnauthorizedException(Throwable cause) {
        super(cause);
    }

    public UnauthorizedException(String description) {
        super(description);
    }

    public UnauthorizedException(int code, String description) {
        super(code, description);
    }

    public UnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UnauthorizedException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }

    @Override
    public ErrorCode defaultErrorCode() {
        return SystemErrorCode.UNAUTHORIZED;
    }
}
