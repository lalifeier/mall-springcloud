package com.github.lalifeier.mall.cloud.common.model.result;


import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.constant.ErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;


public class Result<T> {
  private boolean success;
  private final int code;
  private final String status;
  private final String description;
  private final String message;
  private T data;

  Result(int code, String status, String description, String message, T data) {
    this.code = code;
    this.status = status;
    this.description = description;
    this.message = message;
    this.data = data;
  }

  public static Result<Object> success() {
    return new Result<>(ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getStatus(), null, null, Optional.empty());
  }

  public static <T> Result<T> success(T data) {
    return new Result<>(ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getStatus(), null, null, data);
  }

  public static Result<Object> failure(int code, String message) {
    return new Result<>(code, ErrorCodeEnum.UNKNOWN_ERROR.getStatus(), null, message, Optional.empty());
  }

  public static Result<Object> failure(ErrorCode errorCode) {
    return new Result<>(errorCode.getCode(), errorCode.getStatus(), errorCode.getDescription(), errorCode.getMessage(), Optional.empty());
  }

  public static Result<Object> failure(ErrorCode errorCode, String message) {
    return new Result<>(errorCode.getCode(), errorCode.getStatus(), errorCode.getDescription(), message, Optional.empty());
  }

  public boolean isSuccess() {
    return StringUtils.isNotBlank(status) && status.equals(ErrorCodeEnum.SUCCESS.getStatus());
  }

  public int getCode() {
    return code;
  }

  public String getStatus() {
    return status;
  }

  public String getDescription() {
    return description;
  }

  public String getMessage() {
    return message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
