package com.github.lalifeier.mall.common.model.ddd;


// 聚合根的Marker接口
public interface Aggregate<ID extends Identifier> extends Entity<ID> {

}
