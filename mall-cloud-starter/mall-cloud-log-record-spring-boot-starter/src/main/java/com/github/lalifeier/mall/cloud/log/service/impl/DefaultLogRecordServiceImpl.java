package com.github.lalifeier.mall.cloud.log.service.impl;

import com.github.lalifeier.mall.cloud.log.model.LogRecord;
import com.github.lalifeier.mall.cloud.log.service.LogRecordService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 操作日志存储接口默认实现类 */
public class DefaultLogRecordServiceImpl implements LogRecordService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultLogRecordServiceImpl.class);

    @Override
    public void record(LogRecord logRecord) {
        logger.info("【操作日志】{}", logRecord);
    }

    @Override
    public List<LogRecord> queryLog(String bizNo, String type) {
        return new ArrayList<>();
    }

    @Override
    public List<LogRecord> queryLogByBizNo(String bizNo, String type, String subType) {
        return new ArrayList<>();
    }
}
