package com.github.lalifeier.mall.cloud.gateway.applicaiton.service;

import com.github.lalifeier.mall.cloud.gateway.infrastructure.repository.po.GatewayLog;
import reactor.core.publisher.Mono;

public interface AccessLogService {

  Mono<GatewayLog> saveAccessLog(GatewayLog gatewayLog);
}

