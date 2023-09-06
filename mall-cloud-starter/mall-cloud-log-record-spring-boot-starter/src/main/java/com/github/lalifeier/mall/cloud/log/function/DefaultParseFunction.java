package com.github.lalifeier.mall.cloud.log.function;

import com.github.lalifeier.mall.cloud.log.annotation.OperationLog;

/** 操作日志注解 {@link OperationLog} 的 SpEl 模板中使用的解析函数默认实现 */
public class DefaultParseFunction implements ParseFunction {

    @Override
    public boolean executeBefore() {
        return true;
    }

    @Override
    public String functionName() {
        return null;
    }

    @Override
    public String apply(String value) {
        return null;
    }
}
