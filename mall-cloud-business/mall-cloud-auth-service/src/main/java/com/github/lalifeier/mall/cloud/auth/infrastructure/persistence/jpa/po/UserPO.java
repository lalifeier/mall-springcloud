package com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.po;

import com.github.lalifeier.mall.cloud.jpa.po.BasePO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class UserPO extends BasePO {

  /**
   * 姓名
   */
  private String username;

  /**
   * 密码
   */
  private String password;

  /**
   * 手机号
   */
  private String phone;

  /**
   * 邮箱
   */
  private String email;

  /**
   * 昵称
   */
  private String nickname;

  /**
   * 头像
   */
  private String avatar;

  /**
   * 性别 1:男性 2:女性
   */
  private Integer gender;
}
