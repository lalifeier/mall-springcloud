package com.github.lalifeier.mall.cloud.mybatisplus.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import java.io.Serializable;

public interface BaseEnum<T extends Serializable> extends IEnum<T> {
    T getCode();

    String getDescription();

    @Override
    default T getValue() {
        return getCode();
    }
}
