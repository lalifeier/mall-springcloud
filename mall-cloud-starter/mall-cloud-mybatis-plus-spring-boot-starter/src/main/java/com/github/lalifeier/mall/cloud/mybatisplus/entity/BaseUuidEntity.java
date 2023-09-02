package com.github.lalifeier.mall.cloud.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseUuidEntity {

  @TableId(value = "id", type = IdType.ASSIGN_UUID)
  private UUID id;
}
