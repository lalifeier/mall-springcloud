package com.github.lalifeier.mall.cloud.common.model.result;


import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.manager.ErrorInfo;
import lombok.Data;

@Data
public class Result<T> {
  private boolean success;

  private String status;

  private String message;

  private ErrorInfo error;

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

  public static Result<Object> failure(int errCode, String errMessage) {
    Result<Object> result = new Result<>();
    result.setSuccess(false);
    result.setError(new ErrorInfo(errCode, errMessage));
    return result;
  }

  public static Result<Object> failure(ErrorInfo error) {
    Result<Object> result = new Result<>();
    result.setSuccess(false);
    result.setError(error);
    return result;
  }

//  public static Result<Object> failure(ErrorInfo error, String message) {
//    Result<Object> result = new Result<>();
//    result.setSuccess(false);
//    result.setError(error);
//    result.setMessage(message);
//    return result;
//  }

  public static Result<Object> failure(ErrorCode errorCode) {
    Result<Object> result = new Result<>();
    result.setSuccess(false);
    result.setError(ErrorInfo.parse(errorCode));
    result.setStatus(errorCode.name());
    return result;
  }
}
