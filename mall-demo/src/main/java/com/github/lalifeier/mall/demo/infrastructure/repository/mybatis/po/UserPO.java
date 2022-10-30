package com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "t_user")
public class UserPO {

    @TableField("id")
    private Long id;


    private String username;

    private String password;
}