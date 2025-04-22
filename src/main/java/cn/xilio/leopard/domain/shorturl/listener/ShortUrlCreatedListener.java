package cn.xilio.leopard.domain.shorturl.listener;

import cn.xilio.leopard.domain.shorturl.event.ShortUrlCreatedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class ShortUrlCreatedListener {
    @TransactionalEventListener
    public void handleCreation(ShortUrlCreatedEvent event) {


    }
}
