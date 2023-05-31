package com.github.lalifeier.mall.cloud.jpa.po;

import com.github.lalifeier.mall.cloud.jpa.audit.Auditable;
import com.querydsl.core.BooleanBuilder;
import lombok.Data;
import javax.annotation.Nullable;

import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@EqualsAndHashCode(callSuper=false)
public class BasePO extends Auditable<String> {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "is_deleted")
  private Boolean isDeleted;

  @Nullable
  public BooleanBuilder booleanBuilder() {
    return null;
  }
}
