package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.enums.HttpErrorCodeEnum;
import lombok.Getter;

public class TooManyRequestsException extends SystemException {

    @Getter
    private long limitTimestamp;

    public TooManyRequestsException(Throwable cause) {
        super(cause);
    }

    public TooManyRequestsException(String message) {
        super(message);
    }

    public TooManyRequestsException(ErrorCode errorCode) {
        super(errorCode);
    }

    public TooManyRequestsException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }

    @Override
    public ErrorCode defaultErrorCode() {
        return HttpErrorCodeEnum.TOO_MANY_REQUESTS;
    }
}
