package cn.xilio.leopard.domain.event;

import org.springframework.context.ApplicationEvent;

public class GroupDeleteEvent extends ApplicationEvent {

    public GroupDeleteEvent(Object source) {
        super(source);
    }
}
