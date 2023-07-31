package com.github.lalifeier.mall.cloud.trace.interceptor;

import com.github.lalifeier.mall.cloud.common.constant.HeaderConstants;
import com.github.lalifeier.mall.cloud.common.utils.MDCTraceUtil;
import com.github.lalifeier.mall.cloud.trace.properties.TraceProperties;
import java.io.IOException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class RestTemplateTraceInterceptor implements ClientHttpRequestInterceptor {

    private final TraceProperties traceProperties;

    public RestTemplateTraceInterceptor(TraceProperties traceProperties) {
        this.traceProperties = traceProperties;
    }

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        if (traceProperties.getEnable()) {
            String traceId = MDCTraceUtil.getTraceId();
            request.getHeaders().set(HeaderConstants.TRACE_ID, traceId);
        }

        return execution.execute(request, body);
    }
}
