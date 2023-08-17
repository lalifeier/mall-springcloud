package com.github.lalifeier.mall.cloud.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseEntity<T extends Model<?>> extends Model<T> {

  @TableId(type = IdType.AUTO)
  private Long id;

  @TableField(fill = FieldFill.INSERT)
  private Long createdBy;

  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createdTime;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Long updatedBy;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updatedTime;

  @TableLogic
  @TableField(value = "is_deleted")
  private Boolean isDeleted;
}
