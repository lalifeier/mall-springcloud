package com.github.lalifeier.mall.cloud.common.handler;


import com.github.lalifeier.mall.cloud.common.api.IErrorCodeException;
import com.github.lalifeier.mall.cloud.common.exception.TooManyRequestsException;
import com.github.lalifeier.mall.cloud.common.manager.ErrorInfo;
import com.github.lalifeier.mall.cloud.common.model.result.Result;
import com.github.lalifeier.mall.cloud.common.system.HttpErrorCode;
import com.github.lalifeier.mall.cloud.common.system.SystemErrorCode;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public Result<?> processException(HttpServletRequest request, Exception e) {
    Pair<Throwable, String> pair = getExceptionMessage(e);
    if (e instanceof IErrorCodeException) {
      if (e.getCause() != null) {
        log.error("error, request: {}", parseParam(request), e.getCause());
      } else {
        log.error("error: {}, request: {}", pair.getRight(), parseParam(request));
      }
      ErrorInfo errorInfo = ((IErrorCodeException) e).getErrorInfo();
      if (errorInfo == null) {
        return Result.failure(SystemErrorCode.SYSTEM_ERROR.getCode(), pair.getRight());
      }
      return Result.failure(errorInfo.getCode(), errorInfo.getMsg());
    }
    log.error("error, request: {}", parseParam(request), e);
    return Result.failure(SystemErrorCode.SYSTEM_ERROR.getCode(), pair.getLeft().getClass().getSimpleName() + ": " + pair.getRight());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public Result<?> handleValidatedException(HttpServletRequest request, MethodArgumentNotValidException e) {
    log.error("BadRequestException, request: {}", parseParam(request), e);
    String message =
      e.getBindingResult().getAllErrors().stream()
        .map(ObjectError::getDefaultMessage)
        .collect(Collectors.joining(", "));
    return Result.failure(HttpErrorCode.BAD_REQUEST.getStatus(), message);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = ConstraintViolationException.class)
  public Result<?> handleValidatedException(HttpServletRequest request, ConstraintViolationException e) {
    log.error("BadRequestException, request: {}", parseParam(request), e);
    String message = e.getConstraintViolations().stream()
      .map(ConstraintViolation::getMessage)
      .collect(Collectors.joining(", "));
    return Result.failure(HttpErrorCode.BAD_REQUEST.getStatus(), message);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = BindException.class)
  public Result<?> handleValidatedException(HttpServletRequest request, BindException e) {
    log.error("BadRequestException, request: {}", parseParam(request), e);
    String message = e.getAllErrors().stream()
      .map(ObjectError::getDefaultMessage)
      .collect(Collectors.joining(", "));
    return Result.failure(HttpErrorCode.BAD_REQUEST.getStatus(), message);
  }


  @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
  @ExceptionHandler(value = TooManyRequestsException.class)
  public ResponseEntity<Result<?>> handleTooManyRequestsException(HttpServletRequest request, TooManyRequestsException e) {
//    // 获取限制的时间戳，例如 1633220000 表示 2021-10-03 18:00:00
//    long limitTimestamp = e.getLimitTimestamp();
//    // 获取当前时间戳，例如 1633220500 表示 2021-10-03 18:08:20
//    long currentTimestamp = System.currentTimeMillis() / 1000L;
//    // 计算还剩余的时间，例如剩余 40 秒
//    long remainingSeconds = limitTimestamp - currentTimestamp;
    // 设置响应头信息
    HttpHeaders headers = new HttpHeaders();
//    headers.add("X-RateLimit-Limit", "1000");
//    headers.add("X-RateLimit-Remaining", "950");
//    headers.add("X-RateLimit-Reset", String.valueOf(limitTimestamp));
//    headers.add("Retry-After", String.valueOf(remainingSeconds));

    return ResponseEntity
      .status(HttpStatus.TOO_MANY_REQUESTS)
      .headers(headers)
      .body(Result.failure(HttpStatus.TOO_MANY_REQUESTS.value(), e.getMessage()));
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
