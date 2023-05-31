package com.github.lalifeier.mall.cloud.common.api;



import com.github.lalifeier.mall.cloud.common.system.SystemProjectModule;
import com.google.common.base.Preconditions;

public interface ProjectModule {

    /**
     * 项目编码
     */
    int getProjectCode();

    /**
     * 模块编码
     */
    int getModuleCode();

    /**
     * 项目名称
     */
    String getProjectName();

    /**
     * 模块名称
     */
    String getModuleName();

    static void check(ProjectModule required, ProjectModule input) {
        Preconditions.checkNotNull(required);
        if (input != SystemProjectModule.INSTANCE) {
            Preconditions.checkState(required == input,
                    "module not match, need: " + required.getProjectName() + "-" + required.getModuleName()
                            + "(" + required.getProjectCode() + "-" + required.getModuleCode() + ")"
                            + " but input: " + input.getProjectName() + "-" + input.getModuleName()
                            + "(" + input.getProjectCode() + "-" + input.getModuleCode() + ")");
        }
    }
}
