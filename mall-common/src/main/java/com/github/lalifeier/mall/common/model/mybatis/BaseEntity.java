package com.github.lalifeier.mall.common.model.mybatis;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;

public abstract class BaseEntity<T extends Model<?>> extends Model<T> {

  @TableId
  private Long id;

  @TableField(fill = FieldFill.INSERT)
  private String createdBy;

  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createdAt;

  @TableField(fill = FieldFill.UPDATE)
  private String updatedBy;

  @TableField(fill = FieldFill.UPDATE)
  private LocalDateTime updatedAt;

  @TableLogic
  private Integer is_deleted;
}
