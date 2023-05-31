package com.github.lalifeier.mall.common.error.api;

import com.github.lalifeier.mall.common.error.manager.ErrorManager;

public interface ErrorCode {

    /**
     * 最细粒度code,不包含project、module信息
     */
    int getNodeNum();

    /**
     * 异常信息 英文
     */
    String getMessage();

    /**
     * 拼接project、module、node后的完整的错误码
     */
    default int getCode() {
        return ErrorManager.genCode(this);
    }

    default ProjectModule projectModule() {
        return ErrorManager.projectModule(this);
    }
}