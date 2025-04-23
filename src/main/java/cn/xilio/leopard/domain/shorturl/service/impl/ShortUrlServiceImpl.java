package cn.xilio.leopard.domain.shorturl.service.impl;

import cn.xilio.leopard.domain.shorturl.event.ShortUrlClickedEvent;
import cn.xilio.leopard.domain.shorturl.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    //todo test
    public String getLongUrl(String shortCode, String ip) {
        eventPublisher.publishEvent(new ShortUrlClickedEvent(this, shortCode, ip, LocalDateTime.now()));
        return "a";
    }
}
