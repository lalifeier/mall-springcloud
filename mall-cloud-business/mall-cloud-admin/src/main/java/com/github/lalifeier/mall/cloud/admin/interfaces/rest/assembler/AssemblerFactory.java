package com.github.lalifeier.mall.cloud.admin.interfaces.rest.assembler;

import org.springframework.beans.BeanUtils;

public class AssemblerFactory {
    public <S, T> void assemble(Assembler<S, T> assembler, S source, T target) {
        assembler.assemble(source, target);
    }

    public <S, T> T convert(Assembler<S, T> assembler, S source, Class<T> type) {
        T target = BeanUtils.instantiateClass(type);
        assemble(assembler, source, target);
        return target;
    }
}
