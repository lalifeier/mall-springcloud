package com.github.lalifeier.mall.cloud.gateway.applicaiton.service.impl;

import com.github.lalifeier.mall.cloud.gateway.infrastructure.repository.AccessLogRepository;
import com.github.lalifeier.mall.cloud.gateway.infrastructure.repository.po.GatewayLog;
import com.github.lalifeier.mall.cloud.gateway.applicaiton.service.AccessLogService;
import lombok.AllArgsConstructor;
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

