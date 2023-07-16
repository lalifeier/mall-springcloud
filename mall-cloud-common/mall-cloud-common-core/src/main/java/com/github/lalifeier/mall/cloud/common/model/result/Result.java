package com.github.lalifeier.mall.cloud.common.model.result;


import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import lombok.Data;

@Data
public class Result<T> {
  private boolean success;

  private int code = 200;

  private String status;

  private String message;

  private T data;

  public static Result<Object> success() {
    Result<Object> result = new Result<>();
    result.setSuccess(true);
    return result;
  }

  public static <T> Result<T> success(T data) {
    Result<T> result = new Result<>();
    result.setSuccess(true);
    result.setData(data);
    return result;
  }

  public static Result<Object> failure(int code, String message) {
    Result<Object> result = new Result<>();
    result.setSuccess(false);
    result.setCode(code);
    result.setMessage(message);
    return result;
  }

  public static Result<Object> failure(ErrorCode errorCode) {
    Result<Object> result = new Result<>();
    result.setSuccess(false);
    result.setCode(errorCode.getCode());
    result.setMessage(errorCode.getMessage());
    return result;
  }
}
