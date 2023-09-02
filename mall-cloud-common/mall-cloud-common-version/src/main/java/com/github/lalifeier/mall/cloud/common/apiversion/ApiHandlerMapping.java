package com.github.lalifeier.mall.cloud.common.apiversion;

import com.github.lalifeier.mall.cloud.common.annotation.ApiVersion;
import com.github.lalifeier.mall.cloud.common.condition.ApiVersionCondition;
import java.lang.reflect.Method;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class ApiHandlerMapping extends RequestMappingHandlerMapping {

  /**
   * add @ApiVersion to controller class.
   *
   * @param handlerType handlerType
   * @return RequestCondition
   */
  @Override
  protected RequestCondition<?> getCustomTypeCondition(@NonNull Class<?> handlerType) {
    ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
    return null == apiVersion ? super.getCustomTypeCondition(handlerType)
        : new ApiVersionCondition(apiVersion.value());
  }

  /**
   * add @ApiVersion to controller method.
   *
   * @param method method
   * @return RequestCondition
   */
  @Override
  protected RequestCondition<?> getCustomMethodCondition(@NonNull Method method) {
    ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
    return null == apiVersion ? super.getCustomMethodCondition(method)
        : new ApiVersionCondition(apiVersion.value());
  }

  private RequestCondition<ApiVersionCondition> createCondition(ApiVersion apiVersion) {
    return apiVersion == null ? null : new ApiVersionCondition(apiVersion.value());
  }
}
