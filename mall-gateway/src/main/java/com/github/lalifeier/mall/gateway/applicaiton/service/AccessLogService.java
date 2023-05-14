package com.github.lalifeier.mall.gateway.applicaiton.service;

import com.github.lalifeier.mall.gateway.infrastructure.repository.po.GatewayLog;
import reactor.core.publisher.Mono;

public interface AccessLogService {

  Mono<GatewayLog> saveAccessLog(GatewayLog gatewayLog);
}

