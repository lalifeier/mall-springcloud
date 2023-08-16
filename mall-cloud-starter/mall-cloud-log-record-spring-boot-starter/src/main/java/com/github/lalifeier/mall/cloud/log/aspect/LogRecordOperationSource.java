package com.github.lalifeier.mall.cloud.log.aspect;

//import com.github.lalifeier.mall.cloud.log.annotation.OperationLog;
//import com.github.lalifeier.mall.cloud.log.model.LogRecord;
//import org.springframework.core.BridgeMethodResolver;
//import org.springframework.core.annotation.AnnotatedElementUtils;
//import org.springframework.util.ClassUtils;
//import org.springframework.util.StringUtils;
//
//import java.lang.reflect.AnnotatedElement;
//import java.lang.reflect.Method;
//import java.lang.reflect.Modifier;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.HashSet;
//
//public class LogRecordOperationSource {
//
//
//  public Collection<LogRecord> computeLogRecordOperations(Method method, Class<?> targetClass) {
//    // Don't allow no-public methods as required.
//    if (!Modifier.isPublic(method.getModifiers())) {
//      return Collections.emptyList();
//    }
//
//    // The method may be on an interface, but we need attributes from the target class.
//    // If the target class is null, the method will be unchanged.
//    Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
//    // If we are dealing with method with generic parameters, find the original method.
//    specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
//
//    // First try is the method in the target class.
//    Collection<LogRecord> logRecordOps = parseLogRecordAnnotations(specificMethod);
//    Collection<LogRecord> abstractLogRecordOps = parseLogRecordAnnotations(ClassUtils.getInterfaceMethodIfPossible(method));
//    HashSet<LogRecord> result = new HashSet<>();
//    result.addAll(logRecordOps);
//    result.addAll(abstractLogRecordOps);
//    return result;
//  }
//
//  private Collection<LogRecord> parseLogRecordAnnotations(AnnotatedElement ae) {
//    Collection<LogRecord> logRecordAnnotationAnnotations = AnnotatedElementUtils.findAllMergedAnnotations(ae, LogRecord.class);
//    Collection<LogRecord> ret = new ArrayList<>();
//    if (!logRecordAnnotationAnnotations.isEmpty()) {
//      for (LogRecord recordAnnotation : logRecordAnnotationAnnotations) {
//        ret.add(parseLogRecordAnnotation(ae, recordAnnotation));
//      }
//    }
//    return ret;
//  }
//
//  private LogRecord parseLogRecordAnnotation(AnnotatedElement ae, OperationLog recordAnnotation) {
//    LogRecord recordOps = LogRecord.builder()
//      .success(recordAnnotation.success())
//      .fail(recordAnnotation.fail())
//      .type(recordAnnotation.type())
//      .bizNo(recordAnnotation.bizNo())
//      .operatorId(recordAnnotation.operatorId())
//      .subType(recordAnnotation.subType())
//      .extra(recordAnnotation.extra())
//      .condition(recordAnnotation.condition())
//      .isSuccess(recordAnnotation.condition())
//      .build();
//    validateLogRecordOperation(ae, recordOps);
//    return recordOps;
//  }
//
//
//  private void validateLogRecordOperation(AnnotatedElement ae, LogRecord recordOps) {
//    if (!StringUtils.hasText(recordOps.getSuccessLogTemplate()) && !StringUtils.hasText(recordOps.getFailLogTemplate())) {
//      throw new IllegalStateException("Invalid logRecord annotation configuration on '" +
//        ae.toString() + "'. 'one of successTemplate and failLogTemplate' attribute must be set.");
//    }
//  }
//}
