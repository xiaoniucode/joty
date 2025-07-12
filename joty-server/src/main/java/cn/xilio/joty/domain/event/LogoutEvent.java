package cn.xilio.joty.domain.event;

import org.springframework.context.ApplicationEvent;

public class LogoutEvent extends ApplicationEvent {
    public LogoutEvent(Object source) {
        super(source);
    }
}
