package cn.xilio.leopard.module.biz.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlExpirationTask {
    @Scheduled(cron = "0 0 0 * * ?") // 每天凌晨
    public void checkExpiredUrls() {
        // 查询过期短链接
       // List<ShortUrl> expiredUrls = shortUrlRepository.findExpired(LocalDateTime.now());
       // expiredUrls.forEach(url -> eventPublisher.publishEvent(new ShortUrlExpiredEvent(this, url.getShortCode())));
    }
}
