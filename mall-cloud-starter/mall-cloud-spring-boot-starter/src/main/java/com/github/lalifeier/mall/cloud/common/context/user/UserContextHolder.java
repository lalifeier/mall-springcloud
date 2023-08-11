package com.github.lalifeier.mall.cloud.common.context.user;

import com.alibaba.ttl.TransmittableThreadLocal;

public class UserContextHolder {

    private static final TransmittableThreadLocal<UserContext> THREAD_LOCAL_CONTEXT =
            new TransmittableThreadLocal<>();

    /**
     * 获取当前用户上下文
     *
     * @return null or 当前用户上下文
     */
    public static com.github.lalifeier.mall.cloud.common.context.user.UserContext getUserContext() {
        return THREAD_LOCAL_CONTEXT.get();
    }

    /**
     * 设置当前用户上下文
     *
     * @param newContext 新上下文，传 null 则为清除
     */
    public static void setUserContext(UserContext newContext) {
        if (newContext == null) {
            THREAD_LOCAL_CONTEXT.remove();
        } else {
            THREAD_LOCAL_CONTEXT.set(newContext);
        }
    }

    /** 强制清空本线程的用户上下文，防止影响被线程池复用的其他线程，以及内存泄露 */
    public static void clear() {
        THREAD_LOCAL_CONTEXT.remove();
    }
}
