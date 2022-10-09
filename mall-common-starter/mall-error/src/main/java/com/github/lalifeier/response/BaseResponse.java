package com.github.lalifeier.response;


import com.github.lalifeier.api.ErrorCode;

import java.io.Serializable;


public class BaseResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean success;

    private int errCode;

    private String errMessage;

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

    @Override
    public String toString() {
        return "Response [success=" + success + ", errCode=" + errCode + ", errMessage=" + errMessage + "]";
    }

    public static BaseResponse buildSuccess() {
        BaseResponse response = new BaseResponse();
        response.setSuccess(true);
        return response;
    }

    public static BaseResponse buildFailure(ErrorCode errorCode) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(false);
        response.setErrCode(errorCode.getCode());
        response.setErrMessage(errorCode.getMsg());
        return response;
    }

    public static BaseResponse buildFailure(int errCode, String errMessage) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }
}
