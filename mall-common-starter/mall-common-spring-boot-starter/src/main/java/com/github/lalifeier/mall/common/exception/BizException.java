package com.github.lalifeier.mall.common.exception;

import com.github.lalifeier.mall.common.api.ErrorCode;
import com.github.lalifeier.mall.common.api.ProjectModule;
import com.github.lalifeier.mall.common.manager.ErrorInfo;
import com.github.lalifeier.mall.common.system.SystemProjectModule;

public class BizException extends BaseException {

    private static final long serialVersionUID = 1L;

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(ErrorInfo errorInfo) {
        super(errorInfo);
    }

    public BizException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BizException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }

    @Override
    public ProjectModule projectModule() {
        return SystemProjectModule.INSTANCE;
    }

}