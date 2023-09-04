package com.github.lalifeier.mall.cloud.enums.annocation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.github.lalifeier.mall.cloud.enums.config.EnumAutoConfiguration;

@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(EnumAutoConfiguration.class)
public @interface EnableEnum {

  /**
   * 包路径
   *
   * @return 包路径
   */
  String[] value() default {};

  /**
   * 包路径
   *
   * @return 包路径
   */
  String[] basePackages() default {};

  /**
   * 包路径
   *
   * @return 包路径
   */
  Class<?>[] basePackageClasses() default {};
}
