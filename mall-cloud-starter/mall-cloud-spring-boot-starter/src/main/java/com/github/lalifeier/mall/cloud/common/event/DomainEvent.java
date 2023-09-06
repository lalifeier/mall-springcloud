package com.github.lalifeier.mall.cloud.common.event;

import java.io.Serial;
import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public abstract class DomainEvent<T> extends ApplicationEvent {
    @Serial
    private static final long serialVersionUID = 8057704899566664706L;

    @Getter
    private final String eventId;

    @Getter
    private final Instant occurredOn;

    @Getter
    private final T eventSource;

    public DomainEvent(T eventSource) {
        super(eventSource);
        this.eventId = UUID.randomUUID().toString();
        this.occurredOn = Instant.now();
        this.eventSource = eventSource;
    }
}
