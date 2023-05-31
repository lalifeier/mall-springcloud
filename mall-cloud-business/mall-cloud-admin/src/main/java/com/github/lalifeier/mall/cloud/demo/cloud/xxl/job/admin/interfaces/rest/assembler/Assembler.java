package com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.admin.interfaces.rest.assembler;

@FunctionalInterface
public interface Assembler<S, T> {

    void assemble(S source, T target);

}
