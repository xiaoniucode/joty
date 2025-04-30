package cn.xilio.leopard.listener;


import cn.xilio.leopard.core.config.CacheManager;
import cn.xilio.leopard.domain.CacheKey;
import cn.xilio.leopard.domain.event.ShortUrlCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.util.StringUtils;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Component
@RequiredArgsConstructor
public class ShortUrlCreatedListener {
    private final Logger log = LoggerFactory.getLogger(ShortUrlCreatedListener.class);
    private final CacheManager cacheManager;


    /**
     * Asynchronous processing of short link creation events
     * Using an independent thread pool
     * <p>
     * @ param event is an event object that contains a short code and the original URL
     */
    @Async("shortUrlEventExecutor")
    @EventListener
    //Execute after transaction submission
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleCreation(ShortUrlCreatedEvent event) {
        try {
            log.debug("Processing creation event for short code: {}", event.getShortCode());
            // Double check to prevent cache penetration
            if (StringUtils.hasText(event.getShortCode()) && StringUtils.hasText(event.getLongUrl())) {
                cacheManager.putHash(CacheKey.SHORTURL_URL, event.getShortCode(), event.getLongUrl());
            }
        } catch (Exception e) {
            log.error("Failed to process creation event for code: {}", event.getShortCode(), e);
            throw new RuntimeException("Cache update failed", e);
        }
    }
}


