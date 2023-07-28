package com.github.lalifeier.mall.cloud.jpa.audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> {
  @CreatedDate
  @Column(name = "created_at", columnDefinition = "datetime")
  private LocalDateTime createdAt;

  @CreatedBy
  @Column(name = "created_by")
  private U createdBy;

  @LastModifiedDate
  @Column(name = "updated_at", columnDefinition = "datetime")
  private LocalDateTime updatedAt;

  @LastModifiedBy
  @Column(name = "updated_by")
  private U updatedBy;
}
