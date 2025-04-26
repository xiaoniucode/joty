package cn.xilio.leopard.domain.shorturl.service.impl;

import cn.xilio.leopard.common.exception.BizException;
import cn.xilio.leopard.domain.shorturl.event.ShortUrlClickedEvent;
import cn.xilio.leopard.domain.shorturl.model.ShortUrl;
import cn.xilio.leopard.domain.shorturl.service.DispatcherService;
import cn.xilio.leopard.domain.shorturl.service.ShortUrlService;
import cn.xilio.leopard.domain.shorturl.service.ext.BloomFilterService;
import cn.xilio.leopard.infrastructure.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.function.Function;

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
