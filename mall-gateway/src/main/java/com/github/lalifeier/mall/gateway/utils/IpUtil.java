package com.github.lalifeier.mall.gateway.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

public class IpUtil {
    private static final String IP_UNKNOWN = "unknown";
    private static final String IP_LOCAL = "127.0.0.1";
    private static final String IPV6_LOCAL = "0:0:0:0:0:0:0:1";
    private static final int IP_LEN = 15;

    /**
     * 获取ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddress(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        String ipAddress = headers.getFirst("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = headers.getFirst("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = headers.getFirst("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = Optional.ofNullable(request.getRemoteAddress())
                    .map(address -> address.getAddress().getHostAddress())
                    .orElse("");
            if (IP_LOCAL.equals(ipAddress) || IPV6_LOCAL.equals(ipAddress)) {
                try {
                    InetAddress inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException e) {
                }
            }
        }

        if (ipAddress != null && ipAddress.length() > IP_LEN) {
            int index = ipAddress.indexOf(",");
            if (index > 0) {
                ipAddress = ipAddress.substring(0, index);
            }
        }
        return ipAddress;
    }


    public static Integer getPort(ServerHttpRequest request) {
        return request.getRemoteAddress().getPort();
    }
}

