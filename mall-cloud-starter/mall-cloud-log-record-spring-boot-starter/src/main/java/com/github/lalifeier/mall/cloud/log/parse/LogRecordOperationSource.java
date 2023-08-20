package com.github.lalifeier.mall.cloud.log.parse;

import com.github.lalifeier.mall.cloud.log.annotation.OperationLog;
import com.github.lalifeier.mall.cloud.log.model.LogRecord;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

public class LogRecordOperationSource {

    public Collection<LogRecord> computeLogRecordOperations(Method method, Class<?> targetClass) {
        // Don't allow no-public methods as required.
        if (!Modifier.isPublic(method.getModifiers())) {
            return Collections.emptyList();
        }

        // The method may be on an interface, but we need attributes from the target class.
        // If the target class is null, the method will be unchanged.
        Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
        // If we are dealing with method with generic parameters, find the original method.
        specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);

        // First try is the method in the target class.
        Collection<OperationLog> operationLogs =
                AnnotatedElementUtils.getAllMergedAnnotations(specificMethod, OperationLog.class);
        Collection<LogRecord> list = new ArrayList<>(1);
        if (!operationLogs.isEmpty()) {
            for (OperationLog annotation : operationLogs) {
                list.add(parse(specificMethod, annotation));
            }
        }
        return list;
    }

    private LogRecord parse(AnnotatedElement ae, OperationLog annotation) {
        LogRecord logRecord =
                LogRecord.builder()
                        .bizNo(annotation.bizNo())
                        .type(annotation.type())
                        .subType(annotation.subType())
                        .success(annotation.success())
                        .fail(annotation.fail())
                        .extra(annotation.extra())
                        .operatorId(annotation.operatorId())
                        .operatorName(annotation.operatorName())
                        .condition(annotation.condition())
                        .build();
        validateLogRecordOperation(ae, logRecord);
        return logRecord;
    }

    private void validateLogRecordOperation(AnnotatedElement ae, LogRecord logRecord) {
        if (!StringUtils.hasText(logRecord.getSuccess())
                && !StringUtils.hasText(logRecord.getFail())) {
            throw new IllegalStateException(
                    "Invalid logRecord annotation configuration on '"
                            + ae.toString()
                            + "'. 'one of success and fail' attribute must be set.");
        }
    }
}
