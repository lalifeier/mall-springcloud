package com.github.lalifeier.mall.common.advice;

import com.github.lalifeier.mall.common.dto.Response;
import com.github.lalifeier.mall.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleBizException(BizException ex) {
        log.error("==========================handleBizException==============================");
        log.error(ex.getMessage(), ex);
        return Response.buildFailure(ex.getErrCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Response handleException(Exception ex) {
        log.error("==========================handleException==============================");
        log.error(ex.getMessage(), ex);
        return Response.buildFailure(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage());
    }
}
