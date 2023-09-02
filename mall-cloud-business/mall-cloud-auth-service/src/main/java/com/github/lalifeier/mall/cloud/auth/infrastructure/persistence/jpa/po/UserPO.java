package com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.po;

import com.github.lalifeier.mall.cloud.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user")
public class UserPO extends BaseEntity<Long> {

  /** 姓名 */
  private String username;

  /** 密码 */
  private String password;

  /** 手机号 */
  private String phone;

  /** 邮箱 */
  private String email;

  /** 昵称 */
  private String nickname;

  /** 头像 */
  private String avatar;

  /** 性别 1:男性 2:女性 */
  private Integer gender;
}
