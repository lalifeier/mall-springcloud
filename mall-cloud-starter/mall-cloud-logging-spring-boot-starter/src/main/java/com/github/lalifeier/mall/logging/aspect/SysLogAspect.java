package com.github.lalifeier.mall.logging.aspect;

import com.github.lalifeier.mall.logging.annotation.SysLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

@Aspect
public class SysLogAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(com.github.lalifeier.mall.logging.annotation.SysLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        String className = pjp.getTarget().getClass().getName();
        String methodName = signature.getName();
        SysLog syslog = method.getAnnotation(SysLog.class);
        String operator = syslog.value();
        long beginTime = System.currentTimeMillis();

        Object returnValue = null;
        Exception ex = null;
        try {
            returnValue = pjp.proceed();
            return returnValue;
        } catch (Exception e) {
            ex = e;
            throw e;
        } finally {
            long cost = System.currentTimeMillis() - beginTime;
            if (ex != null) {
                log.error("[class: {}][method: {}][operator: {}][cost: {}ms][args: {}][发生异常]",
                        className, methodName, operator, pjp.getArgs(), ex);
            } else {
                log.info("[class: {}][method: {}][operator: {}][cost: {}ms][args: {}][return: {}]",
                        className, methodName, operator, cost, pjp.getArgs(), returnValue);
            }
        }

    }
}
