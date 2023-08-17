package com.github.lalifeier.mall.cloud.log.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogRecord {

  /**
   * 业务 ID
   */
  private String bizNo;

  /**
   * 操作日志的类型
   */
  private String type;

  /**
   * 操作日志日志的子类型
   */
  private String subType;

  /**
   * 成功信息
   */
  private String success;

  /**
   * 失败信息
   */
  private String fail;

  /**
   * 额外信息
   */
  private String extra;

  /**
   * 操作者ID
   */
  private String operatorId;

  /**
   * 操作者名
   */
  private String operatorName;

  /**
   * 日志记录条件
   */
  private String condition;
}
