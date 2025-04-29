package cn.xilio.leopard.module.biz.service.impl;


import cn.xilio.leopard.module.biz.service.DispatcherService;
import cn.xilio.leopard.module.biz.service.ShortUrlService;
import cn.xilio.leopard.module.biz.domain.dataobject.ShortUrl;

import cn.xilio.leopard.module.biz.domain.service.BloomFilterService;
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

    private final static String cacheKey = "shorturl:url";

    /**
     * Obtain long links
     *
     * @param code short code
     * @return Long link
     */
    @Override
    public String getLongUrl(String code) {
        BizException.checkExpr("1001", !bloomFilterService.contain(code));
        String longUrl = cacheManager.getHash(cacheKey, code, key -> {
            ShortUrl shortUrl = shortUrlService.getByShortCode(code);
            return shortUrl.getOriginalUrl();
        });
        BizException.checkExpr("1001", !StringUtils.hasText(longUrl));
        //eventPublisher.publishEvent( );
        return longUrl;
    }
}
