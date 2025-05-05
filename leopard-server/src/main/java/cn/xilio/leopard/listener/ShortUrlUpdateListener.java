package cn.xilio.leopard.listener;


import cn.xilio.leopard.core.config.CacheManager;
import cn.xilio.leopard.domain.CacheKey;
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

@Component
@RequiredArgsConstructor
public class ShortUrlUpdateListener {
    private final Logger log = LoggerFactory.getLogger(ShortUrlUpdateListener.class);
    private final CacheManager cacheManager;

    @Async("shortUrlEventExecutor")
    @EventListener
    //Execute after transaction submission
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleCreation(ShortUrlUpdateEvent event) {
        try {
            if (event.getStatus()==0){
                //clear hash cache of shortcode
            }
        } catch (Exception e) {

            throw new RuntimeException("Cache update failed", e);
        }
    }
}


