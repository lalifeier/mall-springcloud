package com.github.lalifeier.mall.cloud.common.context;

import lombok.Data;

@Data
public class AppContext {
    private String traceId;
    RequestContext requestContext;
    UserContext userContext;
}
