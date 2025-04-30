package cn.xilio.leopard.task;

import cn.xilio.leopard.service.ShortUrlService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ShortUrlExpirationTask {
    private final ShortUrlService shortUrlService;
    private final Logger log = LoggerFactory.getLogger(ShortUrlExpirationTask.class);
    @Scheduled(cron = "${leopard.task.expire-url-clean:0 0 0 * * ?}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void checkExpiredUrls() {
        try {
            long startTime = System.currentTimeMillis();
            long deletedCount = shortUrlService.deleteExpiredUrls();
            log.info("清理完成，共删除{}条过期记录，耗时{}ms",
                    deletedCount, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            log.error("过期短链接清理任务执行失败", e);
            throw e; // 触发重试
        }
    }
}
