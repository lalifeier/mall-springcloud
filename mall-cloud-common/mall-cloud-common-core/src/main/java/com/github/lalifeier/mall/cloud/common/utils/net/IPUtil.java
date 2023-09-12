package com.github.lalifeier.mall.cloud.common.utils.net;

import com.google.common.net.InetAddresses;
import com.google.common.primitives.Ints;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPUtil {

    /**
     * 从InetAddress转化到int
     */
    public static int toInt(InetAddress address) {
        return InetAddresses.coerceToInteger(address);
    }

    /**
     * InetAddress转换为String
     */
    public static String toIpString(InetAddress address) {
        return InetAddresses.toAddrString(address);
    }

    /**
     * 从int转换为Inet4Address
     */
    public static Inet4Address fromInt(int address) {
        return InetAddresses.fromInteger(address);
    }

    /**
     * 从String转换为InetAddress
     */
    public static InetAddress fromIpString(String address) {
        return InetAddresses.forString(address);
    }

    /**
     * 从IPv4String转换为InetAddress.
     *
     * IpString如果确定ipv4, 使用本方法减少字符分析消耗 .
     *
     * 先字符串传换为byte[]再调getByAddress(byte[])，避免了调用getByName(ip)可能引起的DNS访问.
     */
    public static Inet4Address fromIpv4String(String address) {
        byte[] bytes = ipv4StringToBytes(address);
        if (bytes == null) {
            return null;
        } else {
            try {
                return (Inet4Address) Inet4Address.getByAddress(bytes);
            } catch (UnknownHostException e) {
                throw new AssertionError(e);
            }
        }
    }

    /**
     * int转换到IPV4 String
     */
    public static String intToIpv4String(int address) {
        StringBuilder sb = new StringBuilder(15);
        sb.append((address >> 24) & 0xff)
                .append('.')
                .append((address >> 16) & 0xff)
                .append('.')
                .append((address >> 8) & 0xff)
                .append('.')
                .append(address & 0xff);

        return sb.toString();
    }

    /**
     * Ipv4 String 转换到int
     */
    public static int ipv4StringToInt(String ipv4Str) {
        byte[] byteAddress = ipv4StringToBytes(ipv4Str);
        if (byteAddress == null) {
            return 0;
        } else {
            return Ints.fromByteArray(byteAddress);
        }
    }

    /**
     * Ipv4 String 转换到byte[]
     */
    private static byte[] ipv4StringToBytes(String ipv4Str) {
        try {
            InetAddress inetAddress = InetAddress.getByName(ipv4Str);
            return inetAddress.getAddress();
        } catch (UnknownHostException e) {
            throw new AssertionError(e);
        }
    }
}
