package com.github.lalifeier.mall.cloud.gateway.repository;

import com.github.lalifeier.mall.cloud.gateway.po.GatewayLog;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogRepository extends ReactiveMongoRepository<GatewayLog, String> {

}
