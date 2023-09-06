package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.system.SystemErrorCode;
import lombok.Getter;

public class TooManyRequestsException extends BaseException {

    @Getter
    private long limitTimestamp;

    public TooManyRequestsException(Throwable cause) {
        super(cause);
    }

    public TooManyRequestsException(String description) {
        super(description);
    }

    public TooManyRequestsException(int code, String description) {
        super(code, description);
    }

    public TooManyRequestsException(ErrorCode errorCode) {
        super(errorCode);
    }

    public TooManyRequestsException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }

    public ErrorCode defaultErrorCode() {
        return SystemErrorCode.TOO_MANY_REQUESTS;
    }
}
