package cn.xilio.leopard.listener;


import cn.xilio.leopard.domain.event.ShortUrlCreatedEvent;
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
