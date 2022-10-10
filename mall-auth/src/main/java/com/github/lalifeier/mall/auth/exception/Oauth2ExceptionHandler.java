//package com.github.lalifeier.mall.auth.exception;
//
//import com.github.lalifeier.api.Response;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
//
//@ControllerAdvice
//public class Oauth2ExceptionHandler {
//    @ResponseBody
//    @ExceptionHandler(value = OAuth2Exception.class)
//    public Response<String> handleOauth2(OAuth2Exception e) {
//        return Response.failure(e.getMessage());
//    }
//}