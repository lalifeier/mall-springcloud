package com.github.lalifeier.mall.cloud.gateway.service;

import com.github.lalifeier.mall.cloud.gateway.po.GatewayLog;
import reactor.core.publisher.Mono;

public interface AccessLogService {

  Mono<GatewayLog> saveAccessLog(GatewayLog gatewayLog);
}

