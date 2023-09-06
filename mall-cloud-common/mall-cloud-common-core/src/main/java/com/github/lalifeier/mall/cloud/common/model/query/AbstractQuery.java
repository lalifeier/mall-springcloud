package com.github.lalifeier.mall.cloud.common.model.query;

import java.io.Serializable;
import java.util.Map;
import lombok.Data;

@Data
public abstract class AbstractQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orderBy;

    private String[] columns;

    private Map<String, String> filter;

    public Map<String, String> getFilter() {
        return filter;
    }
}
