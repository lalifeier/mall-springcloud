package com.github.lalifeier.mall.cloud.cache.util;

import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MessageSourceUtil {
  public static String getMsgSource() throws UnknownHostException {
    String host = InetAddress.getLocalHost().getHostAddress();
    Environment env = SpringContextUtil.getBean(Environment.class);
    String port = env.getProperty("server.port");
    return host + ":" + port;
  }
}
