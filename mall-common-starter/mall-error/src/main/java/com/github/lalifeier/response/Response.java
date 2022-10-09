package com.github.lalifeier.response;


import com.github.lalifeier.api.ErrorCode;

public class Response<T> extends BaseResponse {

    private static final long serialVersionUID = 1L;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Response buildSuccess() {
        Response response = new Response();
        response.setSuccess(true);
        return response;
    }

    public static Response buildFailure(ErrorCode errorCode) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrCode(errorCode.getCode());
        response.setErrMessage(errorCode.getMsg());
        return response;
    }

    public static Response buildFailure(int errCode, String errMessage) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }

    public static <T> Response<T> of(T data) {
        Response<T> response = new Response<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }
}
