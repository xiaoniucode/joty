package cn.xilio.leopard.domain.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
@Getter
public class ShortUrlCreatedEvent extends ApplicationEvent {
    public ShortUrlCreatedEvent(Object source) {
        super(source);
    }
}
