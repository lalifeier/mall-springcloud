package com.github.lalifeier.mall.cloud.admin.interfaces.rest.assembler;

@FunctionalInterface
public interface Assembler<S, T> {

    void assemble(S source, T target);

}
