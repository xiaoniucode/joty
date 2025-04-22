package cn.xilio.leopard.domain.shorturl.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlExpiredListener {
    @Async
    @EventListener
    public void handleExpiration(ShortUrlExpiredEvent event) {
       // shortUrlRepository.deleteByShortCode(event.getShortCode());
        //redisTemplate.delete("short_url:" + event.getShortCode());
    }
}
