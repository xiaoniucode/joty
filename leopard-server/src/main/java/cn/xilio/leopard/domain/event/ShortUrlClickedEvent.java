package cn.xilio.leopard.domain.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;
@Getter
public class ShortUrlClickedEvent extends ApplicationEvent {
    private final String shortCode;
    private final String ip;
    private final String referer;
    private final String userAgent;
    private final LocalDateTime clickedAt;

    public ShortUrlClickedEvent(Object source, String shortCode, String ip, String referer, String userAgent, LocalDateTime clickedAt) {
        super(source);
        this.shortCode = shortCode;
        this.ip = ip;
        this.referer = referer;
        this.userAgent = userAgent;
        this.clickedAt = clickedAt;
    }
}
