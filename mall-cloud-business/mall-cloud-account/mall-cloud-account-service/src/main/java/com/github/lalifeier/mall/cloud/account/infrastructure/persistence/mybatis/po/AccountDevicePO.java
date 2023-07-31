package com.github.lalifeier.mall.cloud.account.infrastructure.persistence.mybatis.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.lalifeier.mall.cloud.mybatisplus.po.BasePO;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "account_device")
public class AccountDevicePO extends BasePO<AccountDevicePO> {

    /** 帐号id */
    private Integer accountId;

    /** 应用id */
    private Integer appId;

    /** 应用用户id */
    private Integer appUserId;

    /** 设备类型：0 - 未知，1 - 手机，2 - 平板电脑，3 - pc */
    private int deviceType;

    /** 设备指纹 */
    private String deviceFingerprint;

    /** 设备信息 */
    private String deviceInfo;

    /** 登录ip地址 */
    private String loginIp;

    /** 是否登录：0未登录，1已登录 */
    private int isLogin;

    /** 登录时间 */
    private LocalDateTime loginAt;

    /** 登出时间 */
    private LocalDateTime logoutAt;
}
