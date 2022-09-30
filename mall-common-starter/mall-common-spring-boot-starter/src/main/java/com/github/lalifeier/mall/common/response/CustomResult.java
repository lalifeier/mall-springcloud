package com.github.lalifeier.mall.common.response;

import lombok.Data;

@Data
public class CustomResult<T> {

    private T data;

}

