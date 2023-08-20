package com.github.lalifeier.mall.cloud.log.aspect;

import com.github.lalifeier.mall.cloud.log.context.LogRecordContext;
import com.github.lalifeier.mall.cloud.log.model.ExecuteResult;
import com.github.lalifeier.mall.cloud.log.model.LogRecord;
import com.github.lalifeier.mall.cloud.log.model.MethodInfo;
import com.github.lalifeier.mall.cloud.log.parse.LogRecordOperationSource;
import com.github.lalifeier.mall.cloud.log.parse.LogRecordValueParser;
import java.lang.reflect.Method;
import java.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class OperationLogAspect {

    private LogRecordOperationSource logRecordOperationSource;

    private final LogRecordValueParser logRecordValueParser;

    private final SpelExpressionParser parser = new SpelExpressionParser();

    @Pointcut("@annotation(com.github.lalifeier.mall.cloud.log.annotation.OperationLog)")
    public void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 反射获取类、方法、参数信息
        MethodInfo methodInfo = getMethodInfo(pjp);

        LogRecordContext.putEmptySpan();

        Collection<LogRecord> operations = null;
        Map<String, String> functionNameAndReturnMap = new HashMap<>();
        try {
            operations =
                    logRecordOperationSource.computeLogRecordOperations(
                            methodInfo.getMethod(), methodInfo.getClass());
            List<String> spElTemplates = getBeforeExecuteFunctionTemplate(operations);
            functionNameAndReturnMap =
                    logRecordValueParser.processBeforeExecuteFunctionTemplate(
                            spElTemplates, methodInfo);
        } catch (Exception e) {
            log.error("方法执行前的日志记录解析异常", e);
        }

        Object returnValue = null;
        ExecuteResult executeResult = new ExecuteResult();
        try {
            returnValue = pjp.proceed();
            executeResult.setResult(returnValue);
            executeResult.setSuccess(true);
        } catch (Exception e) {
            executeResult.setSuccess(false);
            executeResult.setThrowable(e);
            executeResult.setErrorMessage(e.getMessage());
        }

        try {
            recordExecute(executeResult, functionNameAndReturnMap, operations);
        } catch (Exception e) {
            log.error("操作日志解析异常", e);
        } finally {
            LogRecordContext.clear();
        }

        if (executeResult.getThrowable() != null) {
            throw executeResult.getThrowable();
        }
        return returnValue;
    }

    private MethodInfo getMethodInfo(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Object[] args = joinPoint.getArgs();
        Class<?> clazz = joinPoint.getTarget().getClass();
        return MethodInfo.builder().method(method).args(args).clazz(clazz).build();
    }

    private void recordExecute(
            ExecuteResult executeResult,
            Map<String, String> functionNameAndReturnMap,
            Collection<LogRecord> operations) {
        for (LogRecord operation : operations) {
            try {
                if (StringUtils.isEmpty(operation.getSuccess())
                        && StringUtils.isEmpty(operation.getFail())) {
                    continue;
                }
            } catch (Exception e) {
                log.error("log record execute exception", e);
            }
        }
    }

    private List<String> getBeforeExecuteFunctionTemplate(Collection<LogRecord> operations) {
        List<String> spElTemplates = new ArrayList<>();
        for (LogRecord operation : operations) {
            // 执行之前的函数，失败模版不解析
            List<String> templates = getSpElTemplates(operation, operation.getSuccess());
            if (!CollectionUtils.isEmpty(templates)) {
                spElTemplates.addAll(templates);
            }
        }
        return spElTemplates;
    }

    private List<String> getSpElTemplates(LogRecord operation, String... actions) {
        List<String> spElTemplates = new ArrayList<>();
        spElTemplates.add(operation.getBizNo());
        spElTemplates.add(operation.getType());
        spElTemplates.add(operation.getSubType());
        spElTemplates.add(operation.getExtra());
        spElTemplates.addAll(Arrays.asList(actions));
        return spElTemplates;
    }
}
