package cn.xilio.leopard.domain.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class ShortUrlCreatedEvent extends ApplicationEvent {
    private final String shortCode;
    private final String longUrl;
    public ShortUrlCreatedEvent(Object source, String shortCode, String longUrl) {
        super(source);
        this.shortCode = Objects.requireNonNull(shortCode);
        this.longUrl = Objects.requireNonNull(longUrl);
    }
}
