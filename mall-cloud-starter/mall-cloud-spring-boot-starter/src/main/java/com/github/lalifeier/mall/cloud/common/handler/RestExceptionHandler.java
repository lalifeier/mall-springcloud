package com.github.lalifeier.mall.cloud.common.handler;

import com.github.lalifeier.mall.cloud.common.enums.ArgumentErrorCodeEnum;
import com.github.lalifeier.mall.cloud.common.enums.ErrorCodeEnum;
import com.github.lalifeier.mall.cloud.common.enums.HttpErrorCodeEnum;
import com.github.lalifeier.mall.cloud.common.enums.ServletErrorCodeEnum;
import com.github.lalifeier.mall.cloud.common.exception.BaseException;
import com.github.lalifeier.mall.cloud.common.exception.BusinessException;
import com.github.lalifeier.mall.cloud.common.exception.TooManyRequestsException;
import com.github.lalifeier.mall.cloud.common.model.result.Result;
import com.google.common.base.Joiner;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class RestExceptionHandler {
    /**
     * 生产环境
     */
    private static final String ENV_PROD = "prod";

    /**
     * 当前环境
     */
    @Value("${spring.profiles.active}")
    private String profile;

    public static final Joiner.MapJoiner JOINER = Joiner.on(",").withKeyValueSeparator(": ");

    // Controller上一层相关异常
    @ExceptionHandler({
        NoHandlerFoundException.class,
        HttpRequestMethodNotSupportedException.class,
        HttpMediaTypeNotSupportedException.class,
        HttpMediaTypeNotAcceptableException.class,
        MissingPathVariableException.class,
        MissingServletRequestParameterException.class,
        TypeMismatchException.class,
        HttpMessageNotReadableException.class,
        HttpMessageNotWritableException.class,
        // BindException.class,
        // MethodArgumentNotValidException.class
        ServletRequestBindingException.class,
        ConversionNotSupportedException.class,
        MissingServletRequestPartException.class,
        AsyncRequestTimeoutException.class
    })
    public Result<?> handleServletException(
            HttpServletRequest request, HttpServletResponse response, Exception exception) {
        log.error("Controller上一层相关异常：{}", exception.getMessage());

        try {
            ServletErrorCodeEnum servletErrorCodeEnum =
                    ServletErrorCodeEnum.valueOf(exception.getClass().getSimpleName());

            response.setStatus(servletErrorCodeEnum.getHttpCode());

            if (ENV_PROD.equals(profile)) {
                return Result.failure(servletErrorCodeEnum);
            }

            return Result.failure(servletErrorCodeEnum, exception.getMessage());
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpErrorCodeEnum.INTERNAL_SERVER_ERROR.getHttpCode());

            if (ENV_PROD.equals(profile)) {
                return Result.failure(HttpErrorCodeEnum.INTERNAL_SERVER_ERROR);
            }
            return Result.failure(HttpErrorCodeEnum.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    // 处理请求参数格式错误 Get请求中 @ModelAttribute或空注解 上校验失败后抛出的异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BindException.class)
    public Result<?> handleValidatedException(HttpServletRequest request, BindException exception) {
        log.error("参数异常：{}", exception.getMessage());
        return handleValidatedException(request, exception.getAllErrors());
    }

    // 处理请求参数格式错误 @RequestBody 上校验失败后抛出的异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<?> handleValidatedException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        log.error("参数异常：{}", exception.getMessage());
        return handleValidatedException(request, exception.getAllErrors());
    }

    // 处理请求参数格式错误 @PathVariable或@RequestParam 上校验失败后抛出的异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result<?> handleValidatedException(HttpServletRequest request, ConstraintViolationException exception) {
        log.error("参数异常：{}", exception.getMessage());
        return handleValidatedException(request, exception.getConstraintViolations());
    }

    private Result<?> handleValidatedException(HttpServletRequest request, Collection<?> errors) {
        String message = getErrorMessage(errors);
        return Result.failure(ArgumentErrorCodeEnum.VALID_ERROR, message);
    }

    private String getErrorMessage(Collection<?> errors) {
        return errors.stream()
                .map(error -> {
                    if (error instanceof FieldError) {
                        return ((FieldError) error).getDefaultMessage();
                    } else if (error instanceof ObjectError) {
                        return ((ObjectError) error).getDefaultMessage();
                    } else if (error instanceof ConstraintViolation) {
                        return ((ConstraintViolation) error).getMessage();
                    } else {
                        return error.toString();
                    }
                })
                .filter(message -> message != null && !message.isEmpty())
                .collect(Collectors.joining(", "));
    }

    // 业务异常
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = BusinessException.class)
    public Result<?> handleBusinessException(
            HttpServletRequest request, HttpServletResponse response, BusinessException exception) {
        log.error("业务异常：{}", exception.getMessage());
        return Result.failure(exception.getErrorCode());
    }

    // 自定义异常
    @ExceptionHandler(value = BaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> handleBaseException(
            HttpServletRequest request, HttpServletResponse response, BaseException exception) {
        log.error("自定义异常：{}", exception.getMessage());
        return Result.failure(exception.getErrorCode());
    }

    // 未定义异常
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> handleException(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        log.error("未定义异常：{}", exception.getMessage());
        if (ENV_PROD.equals(profile)) {
            return Result.failure(ErrorCodeEnum.UNKNOWN_ERROR);
        }

        Pair<Throwable, String> pair = getExceptionMessage(exception);
        String message = pair.getLeft().getClass().getSimpleName() + ": " + pair.getRight();
        return Result.failure(ErrorCodeEnum.UNKNOWN_ERROR, message);
    }

    @ExceptionHandler(value = TooManyRequestsException.class)
    public ResponseEntity<Result<?>> handleTooManyRequestsException(
            HttpServletRequest request, TooManyRequestsException exception) {
        log.error("请求过多：{}", exception.getMessage());

        long limitTimestamp = exception.getLimitTimestamp();
        long currentTimestamp = System.currentTimeMillis() / 1000L;
        long remainingSeconds = limitTimestamp - currentTimestamp;

        HttpHeaders headers = new HttpHeaders();
        // headers.add("X-RateLimit-Limit", "1000");
        // headers.add("X-RateLimit-Remaining", "950");
        headers.add("X-RateLimit-Reset", String.valueOf(limitTimestamp));
        headers.add("Retry-After", String.valueOf(remainingSeconds));

        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .headers(headers)
                .body(Result.failure(HttpErrorCodeEnum.TOO_MANY_REQUESTS));
    }

    public Pair<Throwable, String> getExceptionMessage(Throwable e) {
        Throwable detail = e;
        while (detail.getCause() != null) {
            detail = detail.getCause();
        }
        return ImmutablePair.of(detail, detail.getMessage());
    }
}
