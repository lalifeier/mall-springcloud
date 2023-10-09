package com.github.lalifeier.mall.cloud.mybatisplus.handler;

import com.github.lalifeier.mall.cloud.common.enums.ErrorCodeEnum;
import com.github.lalifeier.mall.cloud.common.model.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MybatisExceptionHandler {

    /** 主键或UNIQUE索引，数据重复异常 */
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> handleDuplicateKeyException(DuplicateKeyException e, HttpServletRequest request) {
        log.error("数据库中已存在记录: {}", e.getMessage());
        return Result.failure(ErrorCodeEnum.DB_ERROR, "数据库中已存在该记录");
    }

    /** Mybatis系统异常 通用处理 */
    @ExceptionHandler(MyBatisSystemException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> handleCannotFindDataSourceException(MyBatisSystemException e, HttpServletRequest request) {
        String message = e.getMessage();
        if (message.contains("CannotFindDataSourceException")) {
            log.error("未找到数据源: {}", message);
            return Result.failure(ErrorCodeEnum.DB_ERROR, "未找到数据源");
        }

        log.error("Mybatis系统异常: {}", message);
        return Result.failure(ErrorCodeEnum.DB_ERROR, "Mybatis系统异常");
    }
}
