package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.enums.ErrorCodeEnum;

public class RemoteServiceException extends SystemException {

    public RemoteServiceException(Throwable cause) {
        super(cause);
    }

    public RemoteServiceException(String message) {
        super(message);
    }

    public RemoteServiceException(ErrorCode errorCode) {
        super(errorCode);
    }

    public RemoteServiceException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }

    @Override
    public ErrorCode defaultErrorCode() {
        return ErrorCodeEnum.REMOTE_SERVER_ERROR;
    }
}
