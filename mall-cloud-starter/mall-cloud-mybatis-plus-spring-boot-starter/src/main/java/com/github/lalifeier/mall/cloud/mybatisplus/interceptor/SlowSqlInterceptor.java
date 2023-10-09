package com.github.lalifeier.mall.cloud.mybatisplus.interceptor;

import java.sql.Statement;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;

@Slf4j
@Intercepts({
    @Signature(
            type = StatementHandler.class,
            method = "query",
            args = {Statement.class, ResultHandler.class}),
    @Signature(
            type = StatementHandler.class,
            method = "update",
            args = {Statement.class}),
    @Signature(
            type = StatementHandler.class,
            method = "batch",
            args = {Statement.class})
})
public class SlowSqlInterceptor implements Interceptor {
    private final long slowSqlInMilliseconds = 1000;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        long startTime = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            if (elapsedTime >= slowSqlInMilliseconds) {
                StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
                BoundSql boundSql = statementHandler.getBoundSql();
                String sql = boundSql.getSql();

                log.info("Slow SQL: {}", sql);
                log.info("Execution Time: {}ms", elapsedTime);
            }
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
}
