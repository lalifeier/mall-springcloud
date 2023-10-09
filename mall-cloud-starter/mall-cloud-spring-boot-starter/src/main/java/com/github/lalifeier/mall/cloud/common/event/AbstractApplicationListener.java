package com.github.lalifeier.mall.cloud.common.event;

import org.springframework.context.ApplicationListener;

public abstract class AbstractApplicationListener implements ApplicationListener<BaseEvent> {
    @Override
    public void onApplicationEvent(BaseEvent event) {
        // if(supports(event)){
        // this.handleEvent(event);
        // }

        this.handleEvent(event);
    }

    protected abstract void handleEvent(BaseEvent event);

    // public abstract boolean supports(EventType eventType);
}
