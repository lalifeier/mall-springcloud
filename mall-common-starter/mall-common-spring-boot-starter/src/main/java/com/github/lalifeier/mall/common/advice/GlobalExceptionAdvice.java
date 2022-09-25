package com.github.lalifeier.mall.common.advice;

import com.github.lalifeier.mall.common.dto.Response;
import com.github.lalifeier.mall.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice()
public class GlobalExceptionAdvice {

    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleBizException(BizException e) {
        log.error(e.getMessage(), e);
        return Response.buildFailure(e.getErrCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Response handleRuntimeException(Exception e) {
        log.error(e.getMessage(), e);
        return Response.buildFailure(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage());
    }
}
