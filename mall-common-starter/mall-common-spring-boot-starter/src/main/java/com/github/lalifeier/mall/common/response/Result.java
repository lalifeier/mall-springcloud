package com.github.lalifeier.mall.common.response;

import com.github.lalifeier.mall.common.api.ErrorCode;
import com.github.lalifeier.mall.common.manager.ErrorInfo;
import com.github.lalifeier.mall.common.system.SystemErrorCodes;
import lombok.Getter;

@Getter
public class Result<T> extends ErrorInfo {

    private T data;

    public Result(int code, String msg) {
        super(code, msg);
    }


    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(SystemErrorCodes.SUCCESS.getCode(), SystemErrorCodes.SUCCESS.getMsg());
        result.data = data;
        return result;
    }

    public static <T> Result<T> error(ErrorCode errorCode) {
        return new Result<>(errorCode.getCode(), errorCode.getMsg());
    }

    public static Result<String> error(Integer code, String msg) {
        return new Result<>(code, msg);
    }

}