package com.github.lalifeier.mall.jpa.po;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> {
  @Column(name = "created_at", columnDefinition = "datetime")
  @CreatedDate
  private Date createdAt;

  @Column(name = "created_by")
  @CreatedBy
  private String createdBy;

  @Column(name = "updated_at", columnDefinition = "datetime")
  @LastModifiedDate
  private Date updatedAt;

  @Column(name = "updated_by")
  @LastModifiedBy
  private String updatedBy;
}
