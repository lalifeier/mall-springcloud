package com.github.lalifeier.mall.cloud.auth.infrastructure.error;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.exception.BusinessException;

public class LoginException extends BusinessException {

    public LoginException(Throwable cause) {
        super(cause);
    }

    public LoginException(String description) {
        super(description);
    }

    public LoginException(int code, String description) {
        super(code, description);
    }

    public LoginException(ErrorCode errorCode) {
        super(errorCode);
    }

    public LoginException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }

    @Override
    public ProjectModule projectModule() {
        return ProjectModuleCode.LOGIN;
    }
}
