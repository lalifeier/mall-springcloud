package com.github.lalifeier.handler;


import com.github.lalifeier.api.Response;
import com.github.lalifeier.manager.ErrorInfo;
import com.github.lalifeier.system.HttpCodes;
import com.github.lalifeier.system.SystemError;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    public static final Joiner.MapJoiner JOINER = Joiner.on(",").withKeyValueSeparator(": ");

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Throwable.class)
    public Response<?> processException(HttpServletRequest request, Exception e) {
        Pair<Throwable, String> pair = getExceptionMessage(e);
        if (e instanceof com.github.lalifeier.exception.IErrorCodeException) {
            if (e.getCause() != null) {
                log.error("error, request: {}", parseParam(request), e.getCause());
            } else {
                log.error("error: {}, request: {}", pair.getRight(), parseParam(request));
            }
            ErrorInfo errorInfo = ((com.github.lalifeier.exception.IErrorCodeException) e).getErrorInfo();
            if (errorInfo == null) {
                return Response.failure(SystemError.SYSTEM_ERROR.getCode(), pair.getRight());
            }
            return Response.failure(errorInfo.getCode(), errorInfo.getMsg());
        }
        log.error("error, request: {}", parseParam(request), e);
        return Response.failure(SystemError.SYSTEM_ERROR.getCode(), pair.getLeft().getClass().getSimpleName() + ": " + pair.getRight());
    }

    //@ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    //public ResponseEntity<Response<String>> handleValidatedException(HttpServletRequest request, Exception e) {
    //    String message = "";
    //    if (e instanceof MethodArgumentNotValidException) {
    //        MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
    //        message =
    //                ex.getBindingResult().getAllErrors().stream()
    //                        .map(ObjectError::getDefaultMessage)
    //                        .collect(Collectors.joining(", "));
    //    } else if (e instanceof ConstraintViolationException) {
    //        ConstraintViolationException ex = (ConstraintViolationException) e;
    //        message =
    //                ex.getConstraintViolations().stream()
    //                        .map(ConstraintViolation::getMessage)
    //                        .collect(Collectors.joining(", "));
    //    } else if (e instanceof BindException) {
    //        BindException ex = (BindException) e;
    //        message = ex.getAllErrors().stream()
    //                .map(ObjectError::getDefaultMessage)
    //                .collect(Collectors.joining(", "));
    //    }
    //
    //    log.error("BadRequestException, request: {}", parseParam(request), e);
    //    return new ResponseEntity<>(Response.failure(HttpCodes.BAD_REQUEST.getStatus(), message), HttpStatus.BAD_REQUEST);
    //}

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response<?> handleValidatedException(HttpServletRequest request, MethodArgumentNotValidException e) {
        log.error("BadRequestException, request: {}", parseParam(request), e);
        String message =
                e.getBindingResult().getAllErrors().stream()
                        .map(ObjectError::getDefaultMessage)
                        .collect(Collectors.joining(", "));
        return Response.failure(HttpCodes.BAD_REQUEST.getStatus(), message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Response<?> handleValidatedException(HttpServletRequest request, ConstraintViolationException e) {
        log.error("BadRequestException, request: {}", parseParam(request), e);
        String message = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        return Response.failure(HttpCodes.BAD_REQUEST.getStatus(), message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BindException.class)
    public Response<?> handleValidatedException(HttpServletRequest request, BindException e) {
        log.error("BadRequestException, request: {}", parseParam(request), e);
        String message = e.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return Response.failure(HttpCodes.BAD_REQUEST.getStatus(), message);
    }

    public String parseParam(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        HashMap<String, String> map = new HashMap<>(parameterMap.size());
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            map.put(entry.getKey(), ArrayUtils.isNotEmpty(entry.getValue()) ? entry.getValue()[0] : "");
        }
        return JOINER.join(map);
    }

    public Pair<Throwable, String> getExceptionMessage(Throwable e) {
        Throwable detail = e;
        while (detail.getCause() != null) {
            detail = detail.getCause();
        }
        return ImmutablePair.of(detail, detail.getMessage());
    }
}
