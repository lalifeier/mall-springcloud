package com.github.lalifeier.mall.cloud.common.context.request;

import com.alibaba.ttl.TransmittableThreadLocal;

public class RequestContextHolder {
    private static final TransmittableThreadLocal<com.github.lalifeier.mall.cloud.common.context.request.RequestContext>
            THREAD_LOCAL_CONTEXT = new TransmittableThreadLocal<>();

    /**
     * 获取当前请求上下文
     *
     * @return null or 当前请求上下文
     */
    public static com.github.lalifeier.mall.cloud.common.context.request.RequestContext getRequestContext() {
        return THREAD_LOCAL_CONTEXT.get();
    }

    /**
     * 设置当前请求上下文
     *
     * @param newContext 新上下文，传 null 则为清除
     */
    public static void setRequestContext(RequestContext newContext) {
        if (newContext == null) {
            THREAD_LOCAL_CONTEXT.remove();
        } else {
            THREAD_LOCAL_CONTEXT.set(newContext);
        }
    }

    /** 强制清空本线程的请求上下文，防止影响被线程池复用的其他线程，以及内存泄露 */
    public static void clear() {
        THREAD_LOCAL_CONTEXT.remove();
    }
}
