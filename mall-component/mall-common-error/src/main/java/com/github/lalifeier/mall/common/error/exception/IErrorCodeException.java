package com.github.lalifeier.mall.common.error.exception;


import com.github.lalifeier.mall.common.error.api.ProjectModule;
import com.github.lalifeier.mall.common.error.manager.ErrorInfo;

public interface IErrorCodeException {
    /**
     * 错误信息
     */
    ErrorInfo getErrorInfo();

    /**
     * 服务+模块信息
     */
    ProjectModule projectModule();
}
