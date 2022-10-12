package com.github.lalifeier.exception;


import com.github.lalifeier.api.ProjectModule;
import com.github.lalifeier.manager.ErrorInfo;

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
