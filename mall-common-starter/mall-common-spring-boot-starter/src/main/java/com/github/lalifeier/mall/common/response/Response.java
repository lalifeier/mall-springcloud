package com.github.lalifeier.mall.common.response;

import com.github.lalifeier.mall.common.api.ErrorCode;
import com.github.lalifeier.mall.common.system.SystemErrorCodes;

public class Response<T> {

    private static final long serialVersionUID = 1L;

    private boolean success;

    private int errCode;

    private String errMessage;

    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response [success=" + success + ", errCode=" + errCode + ", errMessage=" + errMessage + "]";
    }

    public static <T> Response<T> buildSuccess() {
        Response response = new Response();
        response.setSuccess(true);
        return response;
    }

    public static <T> Response<T> of(T data) {
        Response response = new Response();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> buildFailure(ErrorCode errorCode) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrCode(errorCode.getCode());
        response.setErrMessage(errorCode.getMsg());
        return response;
    }

    public static Response<String> buildFailure(Integer errCode, String errMessage){
        Response response = new Response();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }
}
