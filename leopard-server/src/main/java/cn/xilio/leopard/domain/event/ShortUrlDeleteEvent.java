package cn.xilio.leopard.domain.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;
import java.util.Objects;

@Getter
public class ShortUrlDeleteEvent extends ApplicationEvent {
    private final List<String> shortCodes;
    public ShortUrlDeleteEvent(Object source, List<String> shortCodes) {
        super(source);
        this.shortCodes = Objects.requireNonNull(shortCodes);
    }
}
