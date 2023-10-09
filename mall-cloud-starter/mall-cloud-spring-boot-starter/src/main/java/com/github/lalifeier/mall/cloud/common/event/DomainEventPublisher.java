package com.github.lalifeier.mall.cloud.common.event;

public interface DomainEventPublisher {

    void publishEvent(BaseDomainEvent event);
}
