package cn.xilio.leopard.domain.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Objects;

@Getter
public class ShortUrlUpdateEvent extends ApplicationEvent {
    private final String id;
    private final String shortCode;
    private final Integer status;
    public ShortUrlUpdateEvent(Object source, String id,String shortCode, Integer status) {
        super(source);
        this.id = Objects.requireNonNull(id);
        this.shortCode = Objects.requireNonNull(shortCode);
        this.status = Objects.requireNonNull(status);
    }
}
