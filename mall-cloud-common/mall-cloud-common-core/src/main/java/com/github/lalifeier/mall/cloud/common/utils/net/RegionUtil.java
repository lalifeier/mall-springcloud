package com.github.lalifeier.mall.cloud.common.utils.net;

import com.github.lalifeier.mall.cloud.common.exception.ServiceException;
import com.github.lalifeier.mall.cloud.common.model.IpRegion;
import java.io.IOException;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.xdb.Searcher;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegionUtil {
    private static final String IP2REGION_XDB = "ip2region.xdb";
    private static final Searcher SEARCHER;
    private static final String UNKNOWN = "Unknown";

    static {
        try {
            // 获取 ip2region.xdb 文件的路径
            String dbPath = Objects.requireNonNull(RegionUtil.class.getResource(IP2REGION_XDB))
                    .getPath();
            // 从文件加载内容到内存缓冲区
            byte[] cBuff = Searcher.loadContentFromFile(dbPath);
            // 使用内存缓冲区创建 Searcher 对象
            SEARCHER = Searcher.newWithBuffer(cBuff);
        } catch (IOException e) {
            throw new ServiceException("Failed to load ip2region.xdb file " + e.getMessage());
        }
    }

    /**
     * 根据 IP 地址获取地理位置信息
     *
     * @param ip IP 地址
     * @return IpRegion 对象，包含地理位置信息
     */
    public static IpRegion getIpRegion(String ip) {
        try {
            // 使用 Searcher 对象查询地理位置信息
            // 格式：国家|区域|省份|城市|ISP
            String region = SEARCHER.search(ip);
            if (StringUtils.isNotEmpty(region)) {
                String[] split = region.split("\\|");
                return new IpRegion(split[0], split[1], split[2], split[3], split[4]);
            }
        } catch (Exception e) {
            log.error("Failed to get IP region for IP: " + ip);
        }
        return null;
    }

    /**
     * 根据 IP 获取国家
     *
     * @param ip IP 地址
     * @return 国家名称
     */
    public static String getCountry(String ip) {
        IpRegion region = getIpRegion(ip);
        return region != null ? region.getCountry() : UNKNOWN;
    }

    /**
     * 根据 IP 获取区域
     *
     * @param ip IP 地址
     * @return 区域名称
     */
    public static String getRegion(String ip) {
        IpRegion region = getIpRegion(ip);
        return region != null ? region.getRegion() : UNKNOWN;
    }

    /**
     * 根据 IP 获取省份
     *
     * @param ip IP 地址
     * @return 省份名称
     */
    public static String getProvinceByIp(String ip) {
        IpRegion region = getIpRegion(ip);
        return region != null ? region.getProvince() : UNKNOWN;
    }

    /**
     * 根据 IP 获取城市
     *
     * @param ip IP 地址
     * @return 城市名称
     */
    public static String getCity(String ip) {
        IpRegion region = getIpRegion(ip);
        return region != null ? region.getCity() : UNKNOWN;
    }

    /**
     * 根据 IP 获取运营商
     *
     * @param ip IP 地址
     * @return 运营商名称
     */
    public static String getIsp(String ip) {
        IpRegion region = getIpRegion(ip);
        return region != null ? region.getIsp() : UNKNOWN;
    }
}
