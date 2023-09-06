package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.system.SystemErrorCode;

public class SystemException extends BaseException {
    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String description) {
        super(description);
    }

    public SystemException(int code, String description) {
        super(code, description);
    }

    public SystemException(ErrorCode errorCode) {
        super(errorCode);
    }

    public SystemException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }

    @Override
    public ErrorCode defaultErrorCode() {
        return SystemErrorCode.SYSTEM_EXCEPTION;
    }
}
