package com.github.lalifeier.mall.cloud.log.model;

import java.lang.reflect.Method;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MethodInfo {
    private Class<?> clazz;
    private Method method;
    private Object[] args;
}
