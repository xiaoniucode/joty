package cn.xilio.leopard.listener;

import cn.xilio.leopard.core.common.service.RegionService;
import cn.xilio.leopard.core.common.util.*;
import cn.xilio.leopard.domain.dataobject.AccessRecord;
import cn.xilio.leopard.domain.event.ShortUrlClickedEvent;

import cn.xilio.leopard.domain.model.IpRegionInfo;
import cn.xilio.leopard.repository.AccessRecordRepository;
import cn.xilio.leopard.service.StatsService;
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
    private RegionService regionService;
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
        record.setReferer(StringUtils.hasText(event.getReferer()) ? event.getReferer() : "直接访问");//todo需国际化
        record.setUserAgent(event.getUserAgent());
        record.setBrowser(BrowserUtils.detectBrowser(event.getUserAgent()));
        record.setOs(OSDetector.detectFromUserAgent(event.getUserAgent()).getName());
        record.setDeviceType(DeviceDetector.detectDevice(event.getUserAgent()).getName());
        record.setNetworkType(NetworkTypeDetector.detectNetworkType(event.getUserAgent()));
        //Need to optimize
        record.setUserType(statsService.getAccessUserType(event.getShortCode(),event.getIp(), event.getUserAgent()).getValue());
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
