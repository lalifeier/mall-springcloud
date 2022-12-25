package com.github.lalifeier.mall.common.model;

public interface Identifiable<ID extends Identifier> {
    ID getId();
}
