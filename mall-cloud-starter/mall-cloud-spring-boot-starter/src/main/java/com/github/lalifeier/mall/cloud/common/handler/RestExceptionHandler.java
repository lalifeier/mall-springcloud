package com.github.lalifeier.mall.cloud.common.handler;


import com.github.lalifeier.mall.cloud.common.api.ErrorCodeException;
import com.github.lalifeier.mall.cloud.common.exception.http.*;
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
    if (e instanceof ErrorCodeException) {
      if (e.getCause() != null) {
        log.error("error, request: {}", parseParam(request), e.getCause());
      } else {
        log.error("error: {}, request: {}", pair.getRight(), parseParam(request));
      }
      ErrorInfo errorInfo = ((ErrorCodeException) e).getErrorInfo();
      if (errorInfo == null) {
        return Result.failure(SystemErrorCode.SYSTEM_ERROR.getCode(), pair.getRight());
      }
      return Result.failure(errorInfo);
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
    return Result.failure(HttpErrorCode.BAD_REQUEST.getCode(), message);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = ConstraintViolationException.class)
  public Result<?> handleValidatedException(HttpServletRequest request, ConstraintViolationException e) {
    log.error("BadRequestException, request: {}", parseParam(request), e);
    String message = e.getConstraintViolations().stream()
      .map(ConstraintViolation::getMessage)
      .collect(Collectors.joining(", "));
    return Result.failure(HttpErrorCode.BAD_REQUEST.getCode(), message);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = BindException.class)
  public Result<?> handleValidatedException(HttpServletRequest request, BindException e) {
    log.error("BadRequestException, request: {}", parseParam(request), e);
    String message = e.getAllErrors().stream()
      .map(ObjectError::getDefaultMessage)
      .collect(Collectors.joining(", "));
    return Result.failure(HttpErrorCode.BAD_REQUEST.getCode(), message);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = BadRequestException.class)
  public Result<?> handleBadRequestException(HttpServletRequest request, BadRequestException e) {
    return Result.failure(HttpErrorCode.BAD_REQUEST);
  }

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(value = UnauthorizedException.class)
  public Result<?> handleUnauthorizedException(HttpServletRequest request, UnauthorizedException e) {
    return Result.failure(HttpErrorCode.UNAUTHORIZED);
  }

  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ExceptionHandler(value = ForbiddenException.class)
  public Result<?> handleForbiddenException(HttpServletRequest request, ForbiddenException e) {
    return Result.failure(HttpErrorCode.FORBIDDEN);
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = NotFoundException.class)
  public Result<?> handleNotFoundException(HttpServletRequest request, NotFoundException e) {
    return Result.failure(HttpErrorCode.NOT_FOUND);
  }

  @ExceptionHandler(value = TooManyRequestsException.class)
  public ResponseEntity<Result<?>> handleTooManyRequestsException(HttpServletRequest request, TooManyRequestsException e) {
    long limitTimestamp = e.getLimitTimestamp();
    long currentTimestamp = System.currentTimeMillis() / 1000L;
    long remainingSeconds = limitTimestamp - currentTimestamp;

    HttpHeaders headers = new HttpHeaders();
//    headers.add("X-RateLimit-Limit", "1000");
//    headers.add("X-RateLimit-Remaining", "950");
    headers.add("X-RateLimit-Reset", String.valueOf(limitTimestamp));
    headers.add("Retry-After", String.valueOf(remainingSeconds));

    return ResponseEntity
      .status(HttpStatus.TOO_MANY_REQUESTS)
      .headers(headers)
      .body(Result.failure(HttpErrorCode.TOO_MANY_REQUESTS));
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = InternalException.class)
  public Result<?> handleInternalException(HttpServletRequest request, InternalException e) {
    return Result.failure(HttpErrorCode.INTERNAL_SERVER_ERROR);
  }

  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  @ExceptionHandler(value = ServiceException.class)
  public Result<?> handleServiceException(HttpServletRequest request, ServiceException e) {
    return Result.failure(HttpErrorCode.SERVICE_UNAVAILABLE);
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
