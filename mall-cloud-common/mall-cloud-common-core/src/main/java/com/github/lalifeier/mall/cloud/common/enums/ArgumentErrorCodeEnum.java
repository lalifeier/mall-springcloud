package com.github.lalifeier.mall.cloud.common.enums;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import lombok.Getter;

@Getter
public enum ArgumentErrorCodeEnum implements ErrorCode {
    VALID_ERROR(1, "参数校验异常"),

    DATA_NOT_NULL(2, "{0}不能为空"),

    DADA_REPEAT(3, "{0}已存在");

    private final int nodeNum;
    private final String description;
    private final String message;

    ArgumentErrorCodeEnum(int nodeNum, String description, String message) {
        this.nodeNum = nodeNum;
        this.description = description;
        this.message = message;
    }

    ArgumentErrorCodeEnum(int nodeNum, String description) {
        this(nodeNum, description, "");
    }

    @Override
    public ProjectModule getProjectModule() {
        return ProjectModuleEnum.ARGUMENT;
    }

    @Override
    public String getStatus() {
        return name();
    }
}
