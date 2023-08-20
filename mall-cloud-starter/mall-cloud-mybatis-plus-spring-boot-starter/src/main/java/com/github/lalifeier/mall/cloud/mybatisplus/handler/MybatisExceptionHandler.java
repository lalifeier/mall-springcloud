package com.github.lalifeier.mall.cloud.mybatisplus.handler;

import com.github.lalifeier.mall.cloud.common.enums.ErrorCodeEnum;
import com.github.lalifeier.mall.cloud.common.model.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MybatisExceptionHandler {

    /** 主键或UNIQUE索引，数据重复异常 */
    @ExceptionHandler(DuplicateKeyException.class)
    public Result<?> handleDuplicateKeyException(
            DuplicateKeyException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',数据库中已存在记录'{}'", requestURI, e.getMessage());
        return Result.failure(ErrorCodeEnum.SYSTEM_EXCEPTION, "数据库中已存在该记录，请联系管理员确认");
    }

    /** Mybatis系统异常 通用处理 */
    @ExceptionHandler(MyBatisSystemException.class)
    public Result<?> handleCannotFindDataSourceException(
            MyBatisSystemException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String message = e.getMessage();
        if (message.contains("CannotFindDataSourceException")) {
            log.error("请求地址'{}', 未找到数据源", requestURI);
            return Result.failure(ErrorCodeEnum.SYSTEM_EXCEPTION, "未找到数据源，请联系管理员确认");
        }
        log.error("请求地址'{}', Mybatis系统异常", requestURI, e);
        return Result.failure(ErrorCodeEnum.SYSTEM_EXCEPTION, message);
    }
}
