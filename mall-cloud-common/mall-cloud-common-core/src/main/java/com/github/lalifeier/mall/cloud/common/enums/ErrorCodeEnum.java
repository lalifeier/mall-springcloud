package com.github.lalifeier.mall.cloud.common.enums;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import lombok.Getter;

@Getter
public enum ErrorCodeEnum implements ErrorCode {
    SUCCESS(200, 0, "请求成功"),
    UNKNOWN_ERROR(500, -1, "未知系统异常"),
    SYSTEM_EXCEPTION(500, 1, "系统异常"),
    BUSINESS_EXCEPTION(500, 2, "业务异常"),
    REMOTE_SERVER_ERROR(3, "远程调用错误");
    private final int httpCode;
    private final int nodeNum;
    private final String description;
    private final String message;

    ErrorCodeEnum(int httpCode, int nodeNum, String description) {
        this(httpCode, nodeNum, description, "");
    }

    ErrorCodeEnum(int httpCode, int nodeNum, String description, String message) {
        this.httpCode = httpCode;
        this.nodeNum = nodeNum;
        this.description = description;
        this.message = message;
    }

    ErrorCodeEnum(int nodeNum, String description) {
        this(500, nodeNum, description, "");
    }

    ErrorCodeEnum(int nodeNum, String description, String message) {
        this(500, nodeNum, description, message);
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
