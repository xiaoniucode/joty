package cn.xilio.leopard.domain.user.event;

import org.springframework.context.ApplicationEvent;

public class LoginEvent extends ApplicationEvent {
    public LoginEvent(Object source) {
        super(source);
    }
}
