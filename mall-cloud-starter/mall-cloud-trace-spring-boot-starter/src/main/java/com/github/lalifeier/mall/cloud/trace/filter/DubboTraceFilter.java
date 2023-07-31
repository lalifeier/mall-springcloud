package com.github.lalifeier.mall.cloud.trace.filter;

import com.github.lalifeier.mall.cloud.common.utils.MDCTraceUtil;
import com.github.lalifeier.mall.cloud.trace.properties.TraceProperties;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;

/** dubbo过滤器，传递traceId */
@Activate(
        group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER},
        order = Ordered.HIGHEST_PRECEDENCE)
public class DubboTraceFilter implements Filter {

    private final TraceProperties traceProperties;

    public DubboTraceFilter(TraceProperties traceProperties) {
        this.traceProperties = traceProperties;
    }

    /** 服务消费者：传递traceId给下游服务 服务提供者：获取traceId并赋值给MDC */
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        boolean isProviderSide = RpcContext.getContext().isProviderSide();
        if (isProviderSide) {
            String traceId = invocation.getAttachment(MDCTraceUtil.TRACE_ID);
            if (StringUtils.isEmpty(traceId)) {
                MDCTraceUtil.addTrace();
            } else {
                MDCTraceUtil.putTrace(traceId);
            }
        } else {
            String traceId = MDCTraceUtil.getTraceId();
            if (!StringUtils.isEmpty(traceId)) {
                invocation.setAttachment(MDCTraceUtil.TRACE_ID, traceId);
            }
        }

        try {
            return invoker.invoke(invocation);
        } finally {
            if (isProviderSide) {
                MDCTraceUtil.removeTrace();
            }
        }
    }
}
