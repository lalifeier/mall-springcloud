package com.github.lalifeier.mall.cloud.jpa.entity;

import com.querydsl.core.BooleanBuilder;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Accessors(chain = true)
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity<T extends Serializable> implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private T id;

  @CreatedDate
  @Column(name = "created_time", columnDefinition = "datetime")
  private LocalDateTime createdTime;

  @CreatedBy
  @Column(name = "created_by")
  private Long createdBy;

  @LastModifiedDate
  @Column(name = "updated_time", columnDefinition = "datetime")
  private LocalDateTime updatedTime;

  @LastModifiedBy
  @Column(name = "updated_by")
  private Long updatedBy;

  @Column(name = "is_deleted")
  private Boolean deleted;

  @Nullable public BooleanBuilder booleanBuilder() {
    return null;
  }
}
