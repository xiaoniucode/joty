package cn.xilio.leopard.common.event;

import org.springframework.context.ApplicationEvent;

public class CacheRefreshEvent extends ApplicationEvent {
    public CacheRefreshEvent(Object source) {
        super(source);
    }
}
