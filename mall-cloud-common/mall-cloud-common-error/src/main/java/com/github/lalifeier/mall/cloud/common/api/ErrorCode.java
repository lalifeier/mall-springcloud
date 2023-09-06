package com.github.lalifeier.mall.cloud.common.api;

import com.github.lalifeier.mall.cloud.common.manager.ErrorManager;

public interface ErrorCode {

    /** 最细粒度code,不包含project、module信息 */
    int getNodeNum();

    /** 拼接project、module、node后的完整的错误码 */
    default int getCode() {
        return ErrorManager.genCode(this);
    }

    /** 异常信息 */
    String getDescription();

    /** 返回给用户的异常消息 */
    default String getMessage() {
        return getDescription();
    }

    default ProjectModule projectModule() {
        return ErrorManager.projectModule(this);
    }
}
