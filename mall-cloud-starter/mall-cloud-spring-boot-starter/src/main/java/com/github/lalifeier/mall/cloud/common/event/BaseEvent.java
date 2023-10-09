package com.github.lalifeier.mall.cloud.common.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public abstract class BaseEvent<T> extends ApplicationEvent {
    @Getter
    private final T eventData;

    public BaseEvent(T source) {
        super(source);
        this.eventData = source;
    }

    public BaseEvent(Object source, T eventData) {
        super(source);
        this.eventData = eventData;
    }
}
