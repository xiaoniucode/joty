package cn.xilio.leopard.service.impl;


import cn.xilio.leopard.core.common.service.RegionService;
import cn.xilio.leopard.core.common.util.IpUtils;
import cn.xilio.leopard.domain.CacheKey;
import cn.xilio.leopard.service.DispatcherService;
import cn.xilio.leopard.service.ShortUrlService;
import cn.xilio.leopard.domain.dataobject.ShortUrl;

import cn.xilio.leopard.domain.service.BloomFilterService;
import cn.xilio.leopard.core.common.exception.BizException;
import cn.xilio.leopard.core.config.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class DispatcherServiceImpl implements DispatcherService {
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private BloomFilterService bloomFilterService;
    @Autowired
    private ShortUrlService shortUrlService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private RegionService regionService;
    /**
     * Obtain long links
     *
     * @param code short code
     * @return Long link
     */
    @Override
    public String getLongUrl(String code) {
        BizException.checkExpr("1001", !bloomFilterService.contain(code));
        String longUrl = cacheManager.getHash(CacheKey.SHORTURL_URL, code, key -> {
            ShortUrl shortUrl = shortUrlService.getByShortCode(code);
            return shortUrl.getOriginalUrl();
        });
        BizException.checkExpr("1001", !StringUtils.hasText(longUrl));
        String clientIpAddress = IpUtils.getClientIp();
        String region = regionService.getRegion(clientIpAddress);
        regionService.close();
        //eventPublisher.publishEvent( );
        return longUrl;
    }
}
