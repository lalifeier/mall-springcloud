package com.github.lalifeier.mall.cloud.demo.interfaces.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

@Component
public class DemoJobHandler {

    // @Override
    @XxlJob("demoJobHandler")
    public ReturnT<String> execute(String... param) throws Exception {

        return ReturnT.SUCCESS;
    }
}
