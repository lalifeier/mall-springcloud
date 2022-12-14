package com.github.lalifeier.mall.auth.infrastructure.error;



import lombok.AllArgsConstructor;
import lombok.Getter;
import com.github.lalifeier.mall.common.error.api.ProjectModule;

@Getter
@AllArgsConstructor
public enum UserProjectCodes implements ProjectModule {
    /**
     * 登录模块
     */
    LOGIN(1, 1, "用户中心", "登录模块"),

    /**
     * 用户管理模块
     */
    USER(1, 2, "用户中心", "用户模块");

    private final int projectCode;
    private final int moduleCode;
    private final String projectName;
    private final String moduleName;

}
