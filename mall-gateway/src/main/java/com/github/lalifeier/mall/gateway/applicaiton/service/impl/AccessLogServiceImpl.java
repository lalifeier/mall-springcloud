package com.github.lalifeier.mall.gateway.applicaiton.service.impl;

import com.github.lalifeier.mall.gateway.applicaiton.service.AccessLogService;
import com.github.lalifeier.mall.gateway.infrastructure.repository.AccessLogRepository;
import com.github.lalifeier.mall.gateway.infrastructure.repository.po.GatewayLog;
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

