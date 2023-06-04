package com.github.lalifeier.mall.cloud.mybatisplus.query;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public class QueryWrapperX<T> extends QueryWrapper<T> {

  public QueryWrapperX<T> likeIfPresent(String column, String val) {
    if (StringUtils.isNotBlank(val)) {
      return (QueryWrapperX<T>) super.like(column, val);
    }
    return this;
  }

  public QueryWrapperX<T> inIfPresent(String column, Collection<? extends Object> values) {
    if (CollectionUtils.isNotEmpty(values)) {
      return (QueryWrapperX<T>) super.in(column, values);
    }
    return this;
  }

  public QueryWrapperX<T> inIfPresent(String column, Object... values) {
    if (ArrayUtils.isNotEmpty(values)) {
      return (QueryWrapperX<T>) super.in(column, values);
    }
    return this;
  }

  public QueryWrapperX<T> eqIfPresent(String column, Object val) {
    if (val != null) {
      return (QueryWrapperX<T>) super.eq(column, val);
    }
    return this;
  }

  public QueryWrapperX<T> neIfPresent(String column, Object val) {
    if (val != null) {
      return (QueryWrapperX<T>) super.ne(column, val);
    }
    return this;
  }

  public QueryWrapperX<T> gtIfPresent(String column, Object val) {
    if (val != null) {
      return (QueryWrapperX<T>) super.gt(column, val);
    }
    return this;
  }

  public QueryWrapperX<T> geIfPresent(String column, Object val) {
    if (val != null) {
      return (QueryWrapperX<T>) super.ge(column, val);
    }
    return this;
  }

  public QueryWrapperX<T> ltIfPresent(String column, Object val) {
    if (val != null) {
      return (QueryWrapperX<T>) super.lt(column, val);
    }
    return this;
  }

  public QueryWrapperX<T> leIfPresent(String column, Object val) {
    if (val != null) {
      return (QueryWrapperX<T>) super.le(column, val);
    }
    return this;
  }

  public QueryWrapperX<T> betweenIfPresent(String column, Object val1, Object val2) {
    if (val1 != null && val2 != null) {
      return (QueryWrapperX<T>) super.between(column, val1, val2);
    }
    if (val1 != null) {
      return (QueryWrapperX<T>) super.ge(column, val1);
    }
    if (val2 != null) {
      return (QueryWrapperX<T>) super.le(column, val2);
    }
    return this;
  }

  // ========== 重写父类方法，方便链式调用 ==========

  @Override
  public QueryWrapperX<T> eq(boolean condition, String column, Object val) {
    super.eq(condition, column, val);
    return this;
  }

  @Override
  public QueryWrapperX<T> eq(String column, Object val) {
    super.eq(column, val);
    return this;
  }

  @Override
  public QueryWrapperX<T> orderByDesc(String column) {
    super.orderByDesc(true, column);
    return this;
  }

  @Override
  public QueryWrapperX<T> last(String lastSql) {
    super.last(lastSql);
    return this;
  }

  @Override
  public QueryWrapperX<T> in(String column, Collection<?> coll) {
    super.in(column, coll);
    return this;
  }
}
