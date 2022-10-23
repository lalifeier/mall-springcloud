package com.github.lalifeier.mall.gateway.service.impl;

import com.github.lalifeier.mall.gateway.entity.GatewayLog;
import com.github.lalifeier.mall.gateway.repository.AccessLogRepository;
import com.github.lalifeier.mall.gateway.service.AccessLogService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class AccessLogServiceImpl implements AccessLogService {
    private AccessLogRepository accessLogRepository;

    @Override
    public Mono<GatewayLog> saveAccessLog(GatewayLog gatewayLog) {
        return accessLogRepository.insert(gatewayLog);
    }
}
