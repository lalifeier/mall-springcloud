package com.github.lalifeier.mall.common.handler;

import com.github.lalifeier.mall.common.dto.Response;
import com.github.lalifeier.mall.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice()
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleBizException(BizException ex) {
        return Response.buildFailure(ex.getErrCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final Response handleRuntimeException(Exception ex) {
        log.debug("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        return Response.buildFailure(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage());
    }

//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleExceptionInternal(ex, Response.buildFailure(String.valueOf(status.value()), ex.getMessage()), headers, status, request);
//    }
}
