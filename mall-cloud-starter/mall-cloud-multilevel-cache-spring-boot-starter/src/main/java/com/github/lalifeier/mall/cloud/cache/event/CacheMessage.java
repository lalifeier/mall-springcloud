package com.github.lalifeier.mall.cloud.cache.event;

import java.io.Serializable;
import lombok.Data;

@Data
public class CacheMessage implements Serializable {

    private static final long serialVersionUID = 7587021626678201246L;

    private String cacheName;
    private Object key;
    private Object value;
    private Integer type;
}
