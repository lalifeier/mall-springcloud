package com.github.lalifeier.result;

import com.github.lalifeier.api.IError;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Result<T> extends BaseResult {

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
        result.setErrCode(errCode);
        result.setErrMessage(errMessage);
        return result;
    }

    public static Result<Object> failure(IError error) {
        Result<Object> result = new Result<>();
        result.setSuccess(false);
        result.setErrCode(error.getCode());
        result.setErrMessage(error.getMessage());
        return result;
    }
}
