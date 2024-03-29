package com.github.lalifeier.mall.cloud.common.enums;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import lombok.Getter;

@Getter
public enum ErrorCodeEnum implements ErrorCode {
    SUCCESS(0, "请求成功"),

    BASE_ERROR(-1, ""),

    UNKNOWN_ERROR(-1, "未知系统异常"),

    SYSTEM_EXCEPTION(-1, "系统异常"),

    BUSINESS_EXCEPTION(-1, "业务异常"),

    DB_ERROR(-1, "数据库异常"),

    REMOTE_SERVER_ERROR(-1, "远程调用错误");

    private final int nodeNum;
    private final String description;
    private final String message;

    ErrorCodeEnum(int nodeNum, String description, String message) {
        this.nodeNum = nodeNum;
        this.description = description;
        this.message = message;
    }

    ErrorCodeEnum(int nodeNum, String description) {
        this(nodeNum, description, "");
    }

    @Override
    public ProjectModule getProjectModule() {
        return ProjectModuleEnum.DEFAULT;
    }

    @Override
    public String getStatus() {
        return name();
    }
}
