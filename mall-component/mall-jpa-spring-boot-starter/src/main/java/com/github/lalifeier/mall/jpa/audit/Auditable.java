package com.github.lalifeier.mall.jpa.audit;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Date;

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
