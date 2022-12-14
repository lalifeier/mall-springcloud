package com.github.lalifeier.common.result;


import lombok.Data;
import lombok.EqualsAndHashCode;
import com.github.lalifeier.mall.common.error.api.ErrorCode;

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

    public static Result<Object> failure(ErrorCode errorCode) {
        Result<Object> result = new Result<>();
        result.setSuccess(false);
        result.setErrCode(errorCode.getCode());
        result.setErrMessage(errorCode.getMessage());
        return result;
    }
}
