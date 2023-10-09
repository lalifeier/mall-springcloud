package com.github.lalifeier.mall.cloud.common.event.impl;

import com.github.lalifeier.mall.cloud.common.event.BaseDomainEvent;
import com.github.lalifeier.mall.cloud.common.event.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DomainEventPublisherImpl implements DomainEventPublisher {

    // private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishEvent(BaseDomainEvent event) {
        log.debug("发布事件 event:{}", event);
        applicationEventPublisher.publishEvent(event);
    }
}
