package com.github.lalifeier.mall.cloud.mybatisplus.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BasePO<T extends Model<?>> extends Model<T> {

  @TableId(type = IdType.AUTO)
  private Long id;

  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createdAt;

  @TableField(fill = FieldFill.INSERT)
  private Long createdBy;

  @TableField(fill = FieldFill.UPDATE)
  private Long updatedBy;

  @TableField(fill = FieldFill.UPDATE)
  private LocalDateTime updatedAt;

  @TableLogic
  @TableField(value = "is_deleted")
  private Boolean isDeleted;
}
