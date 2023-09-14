package com.github.lalifeier.mall.cloud.common.enums;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import lombok.Getter;

@Getter
public enum ServletErrorCodeEnum implements ErrorCode {
    ;

    private final int httpCode;
    private final int nodeNum;
    private final String description;
    private final String message;

    ServletErrorCodeEnum(int httpCode, int nodeNum, String description, String message) {
        this.httpCode = httpCode;
        this.nodeNum = nodeNum;
        this.description = description;
        this.message = message;
    }

    ServletErrorCodeEnum(int httpCode, String description) {
        this(httpCode, httpCode, description, "");
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
