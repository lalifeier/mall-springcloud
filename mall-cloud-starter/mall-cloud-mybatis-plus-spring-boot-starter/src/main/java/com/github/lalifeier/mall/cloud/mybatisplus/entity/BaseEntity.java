package com.github.lalifeier.mall.cloud.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public abstract class BaseEntity<T extends Serializable> implements Serializable {
  @TableId(type = IdType.AUTO)
  private T id;

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
  private Boolean deleted;
}
