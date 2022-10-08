package com.github.lalifeier.mall.common.advice;

import com.github.lalifeier.mall.common.dto.Response;
import com.github.lalifeier.mall.common.exception.BizException;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {
    public static final Joiner.MapJoiner JOINER = Joiner.on(",").withKeyValueSeparator(": ");

//    @ExceptionHandler(BizException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Response handleBizException(BizException ex) {
//        log.error("==========================handleBizException==============================");
//        log.error(ex.getMessage(), ex);
//        return Response.buildFailure(ex.getErrCode(), ex.getMessage());
//    }
//
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public final Response handleException(Exception ex) {
//        log.error("==========================handleException==============================");
//        log.error(ex.getMessage(), ex);
//        return Response.buildFailure(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage());
//    }


//    @ResponseBody
//    @ExceptionHandler(value = Throwable.class)
//    public ResponseEntity<Result<?>> processException(HttpServletRequest request, Exception e) {
//        Pair<Throwable, String> pair = getExceptionMessage(e);
//        if (e instanceof IErrorCodeException) {
//            if (e.getCause() != null) {
//                log.error("error, request: {}", parseParam(request), e.getCause());
//            } else {
//                log.error("error: {}, request: {}", pair.getRight(), parseParam(request));
//            }
//            ErrorInfo errorInfo = ((IErrorCodeException) e).getErrorInfo();
//            Result<?> apiResult;
//            if (errorInfo == null) {
//                apiResult = Result.error(SystemErrorCodes.SYSTEM_ERROR.getCode(), pair.getRight());
//            } else {
//                apiResult = Result.error(errorInfo.getCode(), errorInfo.getMsg());
//            }
//            return new ResponseEntity<>(apiResult, HttpStatus.OK);
//        }
//        log.error("error, request: {}", parseParam(request), e);
//        Result<String> errorResult = Result.error(SystemErrorCodes.SYSTEM_ERROR.getCode(), pair.getLeft().getClass().getSimpleName() + ": " + pair.getRight());
//        return new ResponseEntity<>(errorResult, HttpStatus.OK);
//    }

//    /**
//     * 请求参数异常
//     */
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public ResponseEntity<SingleResponse<String>> badRequestException(HttpServletRequest request, MethodArgumentNotValidException e) {
//        BindingResult bindingResult = e.getBindingResult();
//        StringBuilder builder = new StringBuilder();
//        for (FieldError fieldError : bindingResult.getFieldErrors()) {
//            builder.append(fieldError.getField() + fieldError.getDefaultMessage()).append(", ");
//        }
//        log.error("BadRequestException, request: {}", parseParam(request), e);
//        return new ResponseEntity<>(SingleResponse.buildFailure(HttpCodes.BAD_REQUEST.getStatus(), builder.toString()), HttpStatus.OK);
//    }


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
