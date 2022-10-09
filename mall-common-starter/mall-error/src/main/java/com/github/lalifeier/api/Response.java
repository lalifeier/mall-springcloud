package com.github.lalifeier.api;



import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;

@Data
public class Response<T>  implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success;

    private int errCode;

    private String errMessage;

    private T data;
    public static Response success() {
        Response response = new Response();
        response.setSuccess(true);
        return  response;
    }

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static Response failure(int errCode, String errMessage) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }

    public static Response failure(IError error) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrCode(error.getCode());
        response.setErrMessage(error.getMessage());
        return response;
    }
}
