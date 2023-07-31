package com.github.lalifeier.mall.cloud.common.context;

import java.util.Map;
import lombok.Data;

@Data
public class RequestContext {
    private final String sessionId;
    private final String requestMethod;
    private final String requestUri;
    private final String clientIp;
    private final String userAgent;
    private final String referer;
    private final Map<String, String> headers;
    private final String query;
    private final String payload;
}
