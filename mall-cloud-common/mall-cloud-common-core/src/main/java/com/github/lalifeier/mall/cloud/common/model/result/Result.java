package com.github.lalifeier.mall.cloud.common.model.result;


import com.github.lalifeier.mall.cloud.common.exception.ErrorCode;
import com.github.lalifeier.mall.cloud.common.exception.ErrorCodeEnum;

import java.util.Optional;


public class Result<T> {
  private final boolean success;
  private final int code;
  private final String status;
  private final String description;
  private final String message;
  private Optional<T> data;

  Result(boolean success, int code, String status, String description, String message, Optional<T> data) {
    this.success = success;
    this.code = code;
    this.status = status;
    this.description = description;
    this.message = message;
    this.data = data;
  }

  public static Result<Object> success() {
    return new Result<>(true, ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getStatus(), null, null, Optional.empty());
  }

  public static <T> Result<T> success(T data) {
    return new Result<>(true, ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getStatus(), null, null, Optional.ofNullable(data));
  }

  public static Result<Object> failure(int code, String message) {
    return new Result<>(false, code, ErrorCodeEnum.UN_KNOW.getStatus(), null, message, Optional.empty());
  }

  public static Result<Object> failure(ErrorCode errorCode) {
    return new Result<>(false, errorCode.getCode(), errorCode.getStatus(), errorCode.getDescription(), errorCode.getMessage(), Optional.empty());
  }

  public static Result<Object> failure(ErrorCode errorCode, String message) {
    return new Result<>(false, errorCode.getCode(), errorCode.getStatus(), errorCode.getDescription(), message, Optional.empty());
  }

  public boolean isSuccess() {
    return success;
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

  public Optional<T> getData() {
    return data;
  }

  public void setData(Optional<T> data) {
    this.data = data;
  }
}
