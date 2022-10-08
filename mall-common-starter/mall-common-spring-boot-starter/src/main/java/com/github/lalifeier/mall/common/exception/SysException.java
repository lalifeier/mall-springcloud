package com.github.lalifeier.mall.common.exception;

import com.github.lalifeier.mall.common.api.ErrorCode;
import com.github.lalifeier.mall.common.api.ProjectModule;
import com.github.lalifeier.mall.common.manager.ErrorInfo;
import com.github.lalifeier.mall.common.system.SystemProjectModule;

public class SysException extends BaseException {

    private static final long serialVersionUID = 1L;

    public SysException(String message) {
        super(message);
    }

    public SysException(String message, Throwable cause) {
        super(message, cause);
    }

    public SysException(Throwable cause) {
        super(cause);
    }

    public SysException(ErrorInfo errorInfo) {
        super(errorInfo);
    }

    public SysException(ErrorCode errorCode) {
        super(errorCode);
    }

    public SysException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }

    @Override
    public ProjectModule projectModule() {
        return SystemProjectModule.INSTANCE;
    }
}