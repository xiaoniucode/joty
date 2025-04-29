package cn.xilio.leopard.core.common.event;

import org.springframework.context.ApplicationEvent;

public class CacheRefreshEvent extends ApplicationEvent {
    public CacheRefreshEvent(Object source) {
        super(source);
    }
}
