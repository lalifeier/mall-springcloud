package com.github.lalifeier.mall.cloud.feign.handler;

import com.github.lalifeier.mall.cloud.common.enums.ErrorCodeEnum;
import com.github.lalifeier.mall.cloud.common.model.result.Result;
import feign.FeignException;
import feign.codec.DecodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class FeignExceptionHandler {
  @ExceptionHandler(FeignException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Result<?> handleFeignException(FeignException e) {
    log.error("远程调用出错：{}", e.getMessage());
    return Result.failure(ErrorCodeEnum.REMOTE_SERVER_ERROR);
  }

  @ExceptionHandler(DecodeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Result<?> handleDecodeException(DecodeException e) {
    log.error("远程调用解析返回值时出错：{}", e.getMessage());
    // Throwable cause = e.getCause();
    // if (cause instanceof BizException){
    // BizException bizException = (BizException) cause;
    // }
    return Result.failure(ErrorCodeEnum.REMOTE_SERVER_ERROR.getCode(), "远程调用解析返回值时出错");
  }
}
