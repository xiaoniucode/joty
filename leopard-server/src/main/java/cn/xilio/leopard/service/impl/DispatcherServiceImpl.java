package cn.xilio.leopard.service.impl;


import cn.xilio.leopard.core.common.service.RegionService;
import cn.xilio.leopard.core.common.util.IpUtils;
import cn.xilio.leopard.domain.CacheKey;
import cn.xilio.leopard.domain.enums.ShortUrlStatus;
import cn.xilio.leopard.domain.event.ShortUrlClickedEvent;
import cn.xilio.leopard.domain.model.IpRegionInfo;
import cn.xilio.leopard.service.DispatcherService;
import cn.xilio.leopard.service.ShortUrlService;
import cn.xilio.leopard.domain.dataobject.ShortUrl;

import cn.xilio.leopard.domain.service.BloomFilterService;
import cn.xilio.leopard.core.common.exception.BizException;
import cn.xilio.leopard.core.config.CacheManager;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;

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
    /**
     * Obtain long links
     *
     * @param code short code
     * @return Long link
     */
    @Override
    public String getLongUrl(String code, HttpServletRequest request) {
        BizException.checkExpr("1001", !bloomFilterService.contain(code));
        String longUrl = cacheManager.getHash(CacheKey.SHORTURL_URL, code, key -> {
            ShortUrl shortUrl = shortUrlService.getByShortCode(code);
            if (Objects.equals(shortUrl.getStatus(), ShortUrlStatus.DISABLED.getCode())) {
                throw new BizException("1009");
            }
            return shortUrl.getOriginalUrl();
        });
        BizException.checkExpr("1001", !StringUtils.hasText(longUrl));
        String ip = IpUtils.getClientIp();
        eventPublisher.publishEvent(new ShortUrlClickedEvent(this,code,ip,request.getHeader("Referer"),request.getHeader("User-Agent"),LocalDateTime.now()));
        return longUrl;
    }
}
