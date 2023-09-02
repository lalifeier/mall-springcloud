package com.github.lalifeier.mall.cloud.log.parse;

import com.github.lalifeier.mall.cloud.log.context.LogRecordEvaluationContext;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;

/** 日志记录表达式解析器 */
public class LogRecordExpressionEvaluator extends CachedExpressionEvaluator {

  private final Map<AnnotatedElementKey, Method> targetMethodCache = new ConcurrentHashMap<>(64);
  private final Map<ExpressionKey, Expression> expressionCache = new ConcurrentHashMap<>(64);

  public Object parseExpression(String conditionExpression, AnnotatedElementKey methodKey,
      EvaluationContext evalContext) {
    return getExpression(this.expressionCache, methodKey, conditionExpression).getValue(evalContext,
        Object.class);
  }

  public EvaluationContext createEvaluationContext(Method method, Object[] args,
      Class<?> targetClass, Object result, String errorMsg, BeanFactory beanFactory) {
    Method targetMethod = getTargetMethod(targetClass, method);
    LogRecordEvaluationContext evaluationContext = new LogRecordEvaluationContext(null,
        targetMethod, args, getParameterNameDiscoverer(), result, errorMsg);
    if (beanFactory != null) {
      evaluationContext.setBeanResolver(new BeanFactoryResolver(beanFactory));
    }
    return evaluationContext;
  }

  private Method getTargetMethod(Class<?> targetClass, Method method) {
    AnnotatedElementKey methodKey = new AnnotatedElementKey(method, targetClass);
    return targetMethodCache.computeIfAbsent(methodKey,
        k -> AopUtils.getMostSpecificMethod(method, targetClass));
  }
}
