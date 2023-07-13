package com.github.lalifeier.mall.cloud.common.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
//@Profile({})
public class ApiLogAspect {

  @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
  public void controllerPointcut() {
  }

  @Before("controllerPointcut()")
  public void doBefore(JoinPoint joinPoint) {
//    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//    HttpSession session = request.getSession();
//
//    log.info("请求IP: {} 请求路径: {} 请求方式: {} 方法描述: {} 请求参数: {}",
//      request.getRemoteAddr(), request.getRequestURL(), request.getMethod(),
//      getMethodInfo(joinPoint), JSON.toJSONString(request.getParameterMap()));
  }

//  private String getMethodInfo(JoinPoint point) {
//    ConcurrentHashMap<String, Object> info = new ConcurrentHashMap<>(3);
//
//    info.put("class", point.getTarget().getClass().getSimpleName());
//    info.put("method", point.getSignature().getName());
//
//    String[] parameterNames = ((MethodSignature) point.getSignature()).getParameterNames();
//    ConcurrentHashMap<String, String> args = null;
//
//    if (Objects.nonNull(parameterNames)) {
//      args = new ConcurrentHashMap<>(parameterNames.length);
//      for (int i = 0; i < parameterNames.length; i++) {
//        String value = point.getArgs()[i] != null ? point.getArgs()[i].toString() : "null";
//        args.put(parameterNames[i], value);
//      }
//      info.put("args", args);
//    }
//    return JSON.toJSONString(info);
//  }
}
