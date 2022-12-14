package com.github.lalifeier.common.model;

public interface Identifiable<ID extends Identifier> {
    ID getId();
}
