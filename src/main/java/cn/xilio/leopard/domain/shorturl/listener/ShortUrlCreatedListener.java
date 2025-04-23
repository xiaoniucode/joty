package cn.xilio.leopard.domain.shorturl.listener;

import cn.xilio.leopard.domain.shorturl.event.ShortUrlCreatedEvent;
import cn.xilio.leopard.domain.stats.service.AccessRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlCreatedListener {

    //@TransactionalEventListener
    @Async
    @EventListener
    public void handleCreation(ShortUrlCreatedEvent event) {


    }
}
