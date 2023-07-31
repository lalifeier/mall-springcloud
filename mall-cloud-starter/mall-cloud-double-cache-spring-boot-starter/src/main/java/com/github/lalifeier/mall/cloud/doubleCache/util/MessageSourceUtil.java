package com.github.lalifeier.mall.cloud.doubleCache.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.core.env.Environment;

public class MessageSourceUtil {
    public static String getMsgSource() throws UnknownHostException {
        String host = InetAddress.getLocalHost().getHostAddress();
        Environment env = SpringContextUtil.getBean(Environment.class);
        String port = env.getProperty("server.port");
        return host + ":" + port;
    }
}
