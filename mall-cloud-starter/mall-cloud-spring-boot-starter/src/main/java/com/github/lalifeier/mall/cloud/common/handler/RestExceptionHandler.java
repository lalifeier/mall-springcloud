package com.github.lalifeier.mall.cloud.common.handler;


import com.github.lalifeier.mall.cloud.common.exception.BaseException;
import com.github.lalifeier.mall.cloud.common.exception.ErrorCodeEnum;
import com.github.lalifeier.mall.cloud.common.model.result.Result;
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
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
  public static final Joiner.MapJoiner JOINER = Joiner.on(",").withKeyValueSeparator(": ");

  @ExceptionHandler(value = Throwable.class)
  public Result<?> processException(HttpServletRequest request, HttpServletResponse response, Exception exception) {
    log.error("error, request: {}", parseParam(request), exception);
    if (exception instanceof BaseException) {
      BaseException baseException = (BaseException) exception;
      response.setStatus(baseException.getErrorCode().getHttpCode());
      return Result.failure(baseException.getErrorCode());
    }

    Pair<Throwable, String> pair = getExceptionMessage(exception);
    String message = pair.getLeft().getClass().getSimpleName() + ": " + pair.getRight();
    response.setStatus(ErrorCodeEnum.UN_KNOW.getHttpCode());
    return Result.failure(ErrorCodeEnum.UN_KNOW, message);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public Result<?> handleValidatedException(HttpServletRequest request, MethodArgumentNotValidException exception) {
    log.error("BadRequestException, request: {}", parseParam(request), exception);
    String message =
      exception.getBindingResult().getAllErrors().stream()
        .map(ObjectError::getDefaultMessage)
        .collect(Collectors.joining(", "));
    return Result.failure(ErrorCodeEnum.BAD_REQUEST, message);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = ConstraintViolationException.class)
  public Result<?> handleValidatedException(HttpServletRequest request, ConstraintViolationException exception) {
    log.error("BadRequestException, request: {}", parseParam(request), exception);
    String message = exception.getConstraintViolations().stream()
      .map(ConstraintViolation::getMessage)
      .collect(Collectors.joining(", "));
    return Result.failure(ErrorCodeEnum.BAD_REQUEST, message);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = BindException.class)
  public Result<?> handleValidatedException(HttpServletRequest request, BindException exception) {
    log.error("BadRequestException, request: {}", parseParam(request), exception);
    String message = exception.getAllErrors().stream()
      .map(ObjectError::getDefaultMessage)
      .collect(Collectors.joining(", "));
    return Result.failure(ErrorCodeEnum.BAD_REQUEST, message);
  }

//  @ExceptionHandler(value = TooManyRequestsException.class)
//  public ResponseEntity<Result<?>> handleTooManyRequestsException(HttpServletRequest request, TooManyRequestsException exception) {
//    long limitTimestamp = exception.getLimitTimestamp();
//    long currentTimestamp = System.currentTimeMillis() / 1000L;
//    long remainingSeconds = limitTimestamp - currentTimestamp;
//
//    HttpHeaders headers = new HttpHeaders();
////    headers.add("X-RateLimit-Limit", "1000");
////    headers.add("X-RateLimit-Remaining", "950");
//    headers.add("X-RateLimit-Reset", String.valueOf(limitTimestamp));
//    headers.add("Retry-After", String.valueOf(remainingSeconds));
//
//    return ResponseEntity
//      .status(HttpStatus.TOO_MANY_REQUESTS)
//      .headers(headers)
//      .body(Result.failure(ErrorCodeEnum.TOO_MANY_REQUESTS));
//  }

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
