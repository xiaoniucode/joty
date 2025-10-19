package cn.xilio.joty.listener;

import cn.xilio.joty.core.common.util.*;
import cn.xilio.joty.domain.dataobject.AccessRecord;
import cn.xilio.joty.domain.event.ShortUrlClickedEvent;

import cn.xilio.joty.repository.AccessRecordRepository;
import cn.xilio.joty.service.StatsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ShortUrlClickedListener {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private AccessRecordRepository accessRecordRepository;
    @Autowired
    private StatsService statsService;

    @Async("shortUrlEventExecutor")
    @EventListener
    @Transactional(rollbackOn = Exception.class)
    public void handleClick(ShortUrlClickedEvent event) {
        AccessRecord record = new AccessRecord();
        record.setShortCode(event.getShortCode());
        record.setIpAddress(event.getIp());
        record.setAccessTime(event.getClickedAt());
        record.setReferer(StringUtils.hasText(event.getReferer()) ? event.getReferer() : "直接访问");
        record.setUserAgent(event.getUserAgent());
        record.setBrowser(BrowserUtils.detectBrowser(event.getUserAgent()));
        record.setOs(OSDetector.detectFromUserAgent(event.getUserAgent()).getName());
        record.setDeviceType(DeviceDetector.detectDevice(event.getUserAgent()).getName());
        record.setNetworkType(NetworkTypeDetector.detectNetworkType(event.getUserAgent()));
        record.setUserType(statsService.getAccessUserType(event.getShortCode(),event.getIp(), event.getUserAgent()).getValue());
        accessRecordRepository.saveAccessRecord(record);
    }
}
