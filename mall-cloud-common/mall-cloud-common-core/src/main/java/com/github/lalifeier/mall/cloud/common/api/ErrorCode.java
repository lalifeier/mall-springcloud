package com.github.lalifeier.mall.cloud.common.api;

public interface ErrorCode {

  /**
   * 获取HTTP状态码
   *
   * @return HTTP状态码
   */
  int getHttpCode();

  /**
   * 获取项目模块
   *
   * @return 项目模块
   */
  ProjectModule getProjectModule();

  /**
   * 获取最细粒度的错误码
   *
   * @return 错误码
   */
  int getNodeNum();

  /**
   * 拼接项目、模块和节点后的完整错误码
   *
   * @return 完整错误码
   */
  default int getCode() {
    ProjectModule projectModule = getProjectModule();
    if (projectModule != null) {
      return projectModule.getProjectCode() * 10000 + projectModule.getModuleCode() * 100
          + getNodeNum();
    }
    return getNodeNum();
  }

  /**
   * 获取错误状态
   *
   * @return 错误状态
   */
  String getStatus();

  /**
   * 获取异常信息
   *
   * @return 异常信息
   */
  String getDescription();

  /**
   * 获取返回给用户的异常消息
   *
   * @return 返回给用户的异常消息
   */
  default String getMessage() {
    return getDescription();
  }
}
