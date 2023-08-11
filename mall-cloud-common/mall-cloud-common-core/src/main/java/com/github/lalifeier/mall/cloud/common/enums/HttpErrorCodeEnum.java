package com.github.lalifeier.mall.cloud.common.enums;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import lombok.Getter;

@Getter
public enum HttpErrorCodeEnum implements ErrorCode {
    OK(200, "请求成功"),
    BAD_REQUEST(400, "错误的请求"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "未找到"),
    METHOD_NOT_ALLOWED(405, "不允许的请求方法"),
    TOO_MANY_REQUESTS(429, "请求过多"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用");
    private final int httpCode;
    private final int nodeNum;
    private final String description;
    private final String message;

    HttpErrorCodeEnum(int httpCode, int nodeNum, String description) {
        this(httpCode, nodeNum, description, "");
    }

    HttpErrorCodeEnum(int httpCode, int nodeNum, String description, String message) {
        this.httpCode = httpCode;
        this.nodeNum = nodeNum;
        this.description = description;
        this.message = message;
    }

    HttpErrorCodeEnum(int httpCode, String description) {
        this(httpCode, httpCode, description, "");
    }

    HttpErrorCodeEnum(int httpCode, String description, String message) {
        this(httpCode, httpCode, description, message);
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
