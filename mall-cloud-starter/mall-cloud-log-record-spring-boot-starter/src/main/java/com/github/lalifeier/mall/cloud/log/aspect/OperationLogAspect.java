package com.github.lalifeier.mall.cloud.log.aspect;

import com.github.lalifeier.mall.cloud.log.context.LogRecordContext;
import com.github.lalifeier.mall.cloud.log.model.LogRecord;
import com.github.lalifeier.mall.cloud.log.model.MethodExecuteResult;
import com.github.lalifeier.mall.cloud.log.parse.LogRecordValueParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class OperationLogAspect {

  private final LogRecordValueParser logRecordValueParser;

  private final SpelExpressionParser parser = new SpelExpressionParser();

  @Pointcut("@annotation(com.github.lalifeier.mall.cloud.log.annotation.OperationLog)")
  public void pointCut() {
  }

  @Around("pointCut()")
  public Object around(ProceedingJoinPoint pjp) throws Throwable {
    Method method = ((MethodSignature) pjp.getSignature()).getMethod();
    Object[] args = pjp.getArgs();
    Class<?> clazz = pjp.getTarget().getClass();

    LogRecordContext.putEmptySpan();

//    Collection<LogRecord> operations = null;
//    Map<String, String> functionNameAndReturnMap = new HashMap<>();
//    try {
//      operations = OperationLogParser.parseLogRecordOpsList(method, clazz);
//      if (CollectionUtil.isNotEmpty(operations)) {
//        List<String> spElTemplates = getBeforeExecuteFunctionTemplate(operations);
//        functionNameAndReturnMap = logRecordValueParser.processBeforeExecuteFunctionTemplate(spElTemplates, methodInfo);
//      }
//    } catch (Exception e) {
//      log.error("方法执行前的日志记录解析异常", e);
//    }


    Object returnValue = null;
    MethodExecuteResult methodExecuteResult = new MethodExecuteResult(method, args, clazz);
    try {
      returnValue = pjp.proceed();
      methodExecuteResult.setResult(returnValue);
      methodExecuteResult.setSuccess(true);
    } catch (Exception e) {
      methodExecuteResult.setSuccess(false);
      methodExecuteResult.setThrowable(e);
      methodExecuteResult.setErrorMessage(e.getMessage());
    }

    try {
    } catch (Exception e) {
      log.error("操作日志解析异常", e);
    } finally {
      LogRecordContext.clear();
    }

    if (methodExecuteResult.getThrowable() != null) {
      throw methodExecuteResult.getThrowable();
    }
    return returnValue;
  }

  private List<String> getBeforeExecuteFunctionTemplate(Collection<LogRecord> operations) {
    List<String> spElTemplates = new ArrayList<>();
    for (LogRecord operation : operations) {
      //执行之前的函数，失败模版不解析
      List<String> templates = getSpElTemplates(operation, operation.getSuccess());
      if (!CollectionUtils.isEmpty(templates)) {
        spElTemplates.addAll(templates);
      }
    }
    return spElTemplates;
  }

  private List<String> getSpElTemplates(LogRecord operation, String... actions) {
    List<String> spElTemplates = new ArrayList<>();
    spElTemplates.add(operation.getType());
    spElTemplates.add(operation.getBizNo());
    spElTemplates.add(operation.getSubType());
    spElTemplates.add(operation.getExtra());
    spElTemplates.addAll(Arrays.asList(actions));
    return spElTemplates;
  }

//  private MethodInfo getMethodInfo(JoinPoint joinPoint) {
//    Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
//    Object[] args = joinPoint.getArgs();
//    Class<?> clazz = joinPoint.getTarget().getClass();
//    return MethodInfo.builder()
//      .method(method)
//      .args(args)
//      .clazz(clazz)
//      .build();
//  }

}
