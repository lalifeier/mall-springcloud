package com.github.lalifeier.mall.cloud.common.model.ddd;

/**
 * 值对象标记接口
 *
 * @param <T>
 */
public interface ValueObject<T> extends MarkerInterface {

    /**
     * 值对象通过属性比较
     *
     * @param other
     * @return
     */
    boolean sameValueAs(T other);
}
