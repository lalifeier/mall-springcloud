package com.github.lalifeier.mall.jpa.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public class BasePO {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

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

  @Column(name = "is_deleted")
  private Boolean isDeleted;
}
