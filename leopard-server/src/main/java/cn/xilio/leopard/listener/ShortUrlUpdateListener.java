package cn.xilio.leopard.listener;


import cn.xilio.leopard.core.config.CacheManager;
import cn.xilio.leopard.domain.CacheKey;
import cn.xilio.leopard.domain.enums.ShortUrlStatus;
import cn.xilio.leopard.domain.event.ShortUrlCreatedEvent;
import cn.xilio.leopard.domain.event.ShortUrlUpdateEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ShortUrlUpdateListener {
    private final Logger log = LoggerFactory.getLogger(ShortUrlUpdateListener.class);
    private final CacheManager cacheManager;

    @Async("shortUrlEventExecutor")
    @EventListener
    public void handleCreation(ShortUrlUpdateEvent event) {
        //Clear the short chain cache that is not in a normal state
        clearAbnormalStateShortLinkCache(event);
    }

    private void clearAbnormalStateShortLinkCache(ShortUrlUpdateEvent event) {
        try {
            if (!StringUtils.hasText(event.getShortCode()) || Objects.equals(event.getStatus(), ShortUrlStatus.ENABLED.getCode())) {
                return;
            }
            log.info("ShortUrlUpdateEvent:[{}]", event.getShortCode());
            if (event.getStatus() == 0) {
                //clear hash cache of shortcode
                cacheManager.deleteHash(CacheKey.SHORTURL_URL, event.getShortCode());
            }
        } catch (Exception e) {

            log.error("ShortUrlUpdateEvent", e);
        }
    }
}


