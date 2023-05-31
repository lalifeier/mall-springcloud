package com.github.lalifeier.mall.cloud.jpa.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@JsonSerialize(using = DictSerializer.class)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
public @interface Dict {

  /** 字典类型 */
  String type() default "";
}
