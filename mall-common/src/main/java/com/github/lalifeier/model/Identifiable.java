package com.github.lalifeier.model;

public interface Identifiable<ID extends Identifier> {
    ID getId();
}
