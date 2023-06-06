package com.github.lalifeier.mall.cloud.common.exception;


import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.manager.ErrorInfo;

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