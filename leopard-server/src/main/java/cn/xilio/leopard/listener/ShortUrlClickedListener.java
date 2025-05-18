package cn.xilio.leopard.listener;

import cn.xilio.leopard.core.common.service.RegionService;
import cn.xilio.leopard.core.common.util.IpUtils;
import cn.xilio.leopard.domain.dataobject.AccessRecord;
import cn.xilio.leopard.domain.event.ShortUrlClickedEvent;

import cn.xilio.leopard.domain.model.IpRegionInfo;
import cn.xilio.leopard.repository.AccessRecordRepository;
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
    private AccessRecordRepository accessRecordRepository;
    @Autowired
    private RegionService regionService;

    @Async("shortUrlEventExecutor")
    @EventListener
    public void handleClick(ShortUrlClickedEvent event) {
        AccessRecord record = new AccessRecord();
        record.setShortCode(event.getShortCode());
        record.setIpAddress(event.getIp());
        record.setAccessTime(event.getClickedAt());
        boolean isLocal = IpUtils.isLocal(event.getIp());
        if (!isLocal) {
            String region = regionService.getRegion(event.getIp());
            regionService.close();
            IpRegionInfo regionInfo = IpRegionInfo.parse(region);
            record.setCountry(regionInfo.getCountry());
            record.setProvince(regionInfo.getProvince());
            record.setCity(regionInfo.getCity());
        }
        accessRecordRepository.saveAccessRecord(record);
    }
}
