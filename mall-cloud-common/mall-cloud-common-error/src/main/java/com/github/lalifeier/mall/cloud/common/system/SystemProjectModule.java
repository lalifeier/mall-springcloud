package com.github.lalifeier.mall.cloud.common.system;

import com.github.lalifeier.mall.cloud.common.api.ProjectModule;

public class SystemProjectModule implements ProjectModule {

    public static final SystemProjectModule INSTANCE = new SystemProjectModule();

    @Override
    public int getProjectCode() {
        return 0;
    }

    @Override
    public int getModuleCode() {
        return 0;
    }

    @Override
    public String getProjectName() {
        return "default";
    }

    @Override
    public String getModuleName() {
        return "default";
    }
}
