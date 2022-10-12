package com.github.lalifeier.mall.gateway.service;

import com.github.lalifeier.mall.gateway.entity.GatewayLog;
import reactor.core.publisher.Mono;

public interface AccessLogService {

    Mono<GatewayLog> saveAccessLog(GatewayLog gatewayLog);
}

