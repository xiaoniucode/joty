package cn.xilio.leopard.listener;

import cn.xilio.leopard.domain.event.ShortUrlClickedEvent;
import cn.xilio.leopard.service.AccessRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlClickedListener {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private AccessRecordService accessRecordService;
    //@Autowired
    //private StatsRepository statsRepository;

    @Async
    @EventListener
    public void handleClick(ShortUrlClickedEvent event) {
        //记录访问日志

        String shortCode = event.getShortCode();
        redisTemplate.opsForValue().increment("stats:click:" + shortCode);
        // 异步批量更新 MySQL
        //statsRepository.incrementClickCount(shortCode);
    }
}
