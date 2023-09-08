package com.github.lalifeier.mall.cloud.account.infrastructure.persistence.mybatis.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.lalifeier.mall.cloud.common.enums.StatusEnum;
import com.github.lalifeier.mall.cloud.mybatisplus.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName(value = "account_user")
public class AccountUserPO extends BaseEntity<Long> {
    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 邮箱 */
    private String email;

    /** 手机号 */
    private String phone;

    /** 帐号状态：0 - 无效，1 - 有效 */
    private StatusEnum status;
}
