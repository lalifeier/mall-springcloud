package com.github.lalifeier.mall.common.model.ddd;

// 实体类的Marker接口
public interface Entity<ID extends Identifier> extends Identifiable<ID> {

}