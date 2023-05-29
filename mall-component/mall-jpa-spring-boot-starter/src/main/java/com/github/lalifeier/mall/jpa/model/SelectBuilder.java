package com.github.lalifeier.mall.jpa.model;

import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

/**
 * 查询条件构造器
 */
public abstract class SelectBuilder<T extends Predicate> {

//  @NotNull
//  public static SelectBooleanBuilder booleanBuilder() {
//    return new SelectBooleanBuilder();
//  }
//
//  @NotNull
//  public static SelectBooleanBuilder booleanBuilder(BaseEntity entity) {
//    BooleanBuilder builder = null;
//    if (entity != null) {
//      builder = entity.booleanBuilder();
//    }
//    return new SelectBooleanBuilder(builder);
//  }

  /**
   * 获取查询条件
   *
   * @return 查询条件
   */
  @NotNull
  public abstract T getPredicate();
}
