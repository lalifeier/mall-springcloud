package com.github.lalifeier.mall.cloud.repeatsubmit.annotation;

import com.github.lalifeier.mall.cloud.repeatsubmit.enums.RepeatSubmitType;

import java.lang.annotation.*;

/**
 * 自定义注解防止表单重复提交
 */

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatSubmit {
  /**
   * 间隔时间(ms)，小于此时间视为重复提交
   */
  int interval() default 3000;


  /**
   * 防重复提交的方式，支持 参数 和 token 两种方式，默认为 参数
   */
  RepeatSubmitType type() default RepeatSubmitType.PARAM;

  /**
   * 提示消息
   */
  String message() default "不允许重复提交，请稍后再试";
}

