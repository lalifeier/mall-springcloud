package com.github.lalifeier.mall.cloud.common.enums;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;

/**
 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
 */
@Getter
public enum ServletErrorCodeEnum implements ErrorCode {
    MethodArgumentNotValidException(HttpServletResponse.SC_BAD_REQUEST, "方法参数无效"),

    MethodArgumentTypeMismatchException(HttpServletResponse.SC_BAD_REQUEST, "方法参数类型不匹配"),

    MissingServletRequestPartException(HttpServletResponse.SC_BAD_REQUEST, "缺少 Servlet 请求部分"),

    MissingPathVariableException(HttpServletResponse.SC_BAD_REQUEST, "缺少路径变量"),

    BindException(HttpServletResponse.SC_BAD_REQUEST, "绑定异常"),

    MissingServletRequestParameterException(HttpServletResponse.SC_BAD_REQUEST, "缺少 Servlet 请求参数"),

    TypeMismatchException(HttpServletResponse.SC_BAD_REQUEST, "类型不匹配"),

    ServletRequestBindingException(HttpServletResponse.SC_BAD_REQUEST, "Servlet 请求绑定异常"),

    HttpMessageNotReadableException(HttpServletResponse.SC_BAD_REQUEST, "HTTP 消息不可读"),

    NoHandlerFoundException(HttpServletResponse.SC_NOT_FOUND, "找不到处理程序"),

    NoSuchRequestHandlingMethodException(HttpServletResponse.SC_NOT_FOUND, "找不到请求处理方法"),

    HttpRequestMethodNotSupportedException(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "HTTP 请求方法不支持"),

    HttpMediaTypeNotAcceptableException(HttpServletResponse.SC_NOT_ACCEPTABLE, "HTTP 媒体类型不可接受"),

    HttpMediaTypeNotSupportedException(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "HTTP 媒体类型不支持"),

    ConversionNotSupportedException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "转换不支持"),

    HttpMessageNotWritableException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "HTTP 消息不可写"),

    AsyncRequestTimeoutException(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "异步请求超时");

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
        return ProjectModuleEnum.SERVLET;
    }

    @Override
    public String getStatus() {
        return name();
    }
}
