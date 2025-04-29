package cn.xilio.leopard.domain.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;
@Getter
public class ShortUrlClickedEvent extends ApplicationEvent {
    private final String shortCode;
    private final String ip;
    private final LocalDateTime clickedAt;

    public ShortUrlClickedEvent(Object source, String shortCode, String ip, LocalDateTime clickedAt) {
        super(source);
        this.shortCode = shortCode;
        this.ip = ip;
        this.clickedAt = clickedAt;
    }
}
