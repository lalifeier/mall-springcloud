package com.github.lalifeier.mall.cloud.common.event;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public abstract class BaseDomainEvent<T> extends ApplicationEvent {
    @Getter
    private final String eventId;

    @Getter
    private final LocalDateTime eventOccurTime;

    @Getter
    private final T eventData;

    public BaseDomainEvent(T eventData) {
        super(eventData);
        this.eventId = UUID.randomUUID().toString();
        this.eventOccurTime = LocalDateTime.now();
        this.eventData = eventData;
    }
}
