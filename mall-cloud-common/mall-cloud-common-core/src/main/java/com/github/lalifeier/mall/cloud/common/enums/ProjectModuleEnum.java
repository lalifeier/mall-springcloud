package com.github.lalifeier.mall.cloud.common.enums;

import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProjectModuleEnum implements ProjectModule {
    /** 默认项目模块 */
    DEFAULT(0, 0, "default", "default");

    /** 项目代码 */
    private final int projectCode;

    /** 模块代码 */
    private final int moduleCode;

    /** 项目名称 */
    private final String projectName;

    /** 模块名称 */
    private final String moduleName;
}
