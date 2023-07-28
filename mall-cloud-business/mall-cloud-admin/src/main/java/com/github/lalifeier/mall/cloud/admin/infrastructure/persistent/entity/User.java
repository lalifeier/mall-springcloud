package com.github.lalifeier.mall.cloud.admin.infrastructure.persistent.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "user")
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "username", unique = true, nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email")
  private String email;
}
