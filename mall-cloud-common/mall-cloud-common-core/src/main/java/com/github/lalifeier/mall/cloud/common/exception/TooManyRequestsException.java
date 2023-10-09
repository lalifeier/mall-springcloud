package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.enums.HttpErrorCodeEnum;
import lombok.Getter;

public class TooManyRequestsException extends SystemException {

    @Getter
    private long limitTimestamp;

    public TooManyRequestsException(String message) {
        super(message);
    }

    public TooManyRequestsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooManyRequestsException(Throwable cause) {
        super(cause);
    }

    @Override
    public ErrorCode getErrorCode() {
        return HttpErrorCodeEnum.TOO_MANY_REQUESTS;
    }
}
