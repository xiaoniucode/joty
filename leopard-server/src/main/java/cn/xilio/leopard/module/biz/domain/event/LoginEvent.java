package cn.xilio.leopard.module.biz.domain.event;

import org.springframework.context.ApplicationEvent;

public class LoginEvent extends ApplicationEvent {
    public LoginEvent(Object source) {
        super(source);
    }
}
