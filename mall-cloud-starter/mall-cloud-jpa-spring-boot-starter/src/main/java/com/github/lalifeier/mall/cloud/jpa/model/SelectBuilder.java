package com.github.lalifeier.mall.cloud.jpa.model;

import com.github.lalifeier.mall.cloud.jpa.entity.BaseEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;

/** 查询条件构造器. */
public abstract class SelectBuilder<T extends Predicate> {

  @NotNull public static SelectBooleanBuilder booleanBuilder() {
    return new SelectBooleanBuilder();
  }

  @NotNull public static SelectBooleanBuilder booleanBuilder(BaseEntity entity) {
    BooleanBuilder builder = null;
    if (entity != null) {
      builder = entity.booleanBuilder();
    }
    return new SelectBooleanBuilder(builder);
  }

  /**
   * 获取查询条件
   *
   * @return 查询条件
   */
  @NotNull public abstract T getPredicate();

  public <E> QueryResults<E> from(@NotNull JPAQueryFactory jpaQueryFactory,
      @NotNull EntityPath<E> from, Pageable page) {
    Predicate predicate = getPredicate();
    if (predicate == null) {
      throw new IllegalStateException("SelectBuilder 子类实现的方法不能返回 null");
    }
    JPAQuery<E> selectFrom = jpaQueryFactory.selectFrom(from).where(predicate);
    if (page != null) {
      selectFrom.offset(page.getOffset()).limit(page.getPageSize());
    }
    return selectFrom.fetchResults();
  }

  public <E> JPAQuery<E> from(@NotNull JPAQueryFactory jpaQueryFactory,
      @NotNull EntityPath<E> from) {
    Predicate predicate = getPredicate();
    if (predicate == null) {
      throw new IllegalStateException("SelectBuilder 子类实现的方法不能返回 null");
    }
    return jpaQueryFactory.selectFrom(from).where(predicate);
  }
}
