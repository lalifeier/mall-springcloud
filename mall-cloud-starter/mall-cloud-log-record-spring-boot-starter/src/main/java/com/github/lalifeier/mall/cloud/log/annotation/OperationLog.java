package com.github.lalifeier.mall.cloud.log.annotation;

import java.lang.annotation.*;

/**
 * 操作日志注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface OperationLog {

  /**
   * 业务ID
   * 必填
   * SpEL表达式
   */
  String bizNo();

  /**
   * 操作日志的类型，比如：订单类型、商品类型
   * 必填
   * SpEL表达式
   */
  String type();

  /**
   * 日志的子类型，比如订单的C端日志，和订单的B端日志，type都是订单类型，但是子类型不一样
   * SpEL表达式
   */
  String subType();

  /**
   * 成功信息
   * SpEL表达式
   */
  String success() default "";

  /**
   * 失败信息
   * SpEL表达式
   */
  String fail() default "";

  /**
   * 额外信息
   * SpEL表达式
   */
  String extra() default "";

  /**
   * 操作人ID
   * 可选
   * SpEL表达式
   */
  String operatorId() default "";

  /**
   * 操作人
   * 可选
   * SpEL表达式
   */
  String operatorName() default "";

  /**
   * 日志记录条件
   * 可选
   * SpEL表达式
   */
  String condition() default "'true'";
}
