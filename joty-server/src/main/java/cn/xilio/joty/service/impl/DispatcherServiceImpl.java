package cn.xilio.joty.service.impl;


import cn.xilio.joty.core.common.util.IpUtils;
import cn.xilio.joty.domain.CacheKey;
import cn.xilio.joty.domain.enums.ShortUrlStatus;
import cn.xilio.joty.domain.event.ShortUrlClickedEvent;
import cn.xilio.joty.service.DispatcherService;
import cn.xilio.joty.service.ShortUrlService;
import cn.xilio.joty.domain.dataobject.ShortUrl;

import cn.xilio.joty.domain.service.BloomFilterService;
import cn.xilio.joty.core.common.exception.BizException;
import cn.xilio.joty.core.config.CacheManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
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
    public String getLongUrl(String code, HttpServletRequest request, HttpServletResponse response) {
        if(!bloomFilterService.contain(code)){
            return null;
        }
        String longUrl = cacheManager.getHash(CacheKey.SHORTURL_URL, code, key -> {
            ShortUrl shortUrl = shortUrlService.getByShortCode(code);
            BizException.checkExpr("1009", Objects.equals(shortUrl.getStatus(), ShortUrlStatus.DISABLED.getCode()));
            LocalDate expiredAt = shortUrl.getExpiredAt();
            BizException.checkExpr("1007", (!ObjectUtils.isEmpty(expiredAt) && expiredAt.isBefore(LocalDate.now())));
            return shortUrl.getOriginalUrl();
        });
        if ( !StringUtils.hasText(longUrl)){
            return null;
        }
        String ip = IpUtils.getClientIp();
        eventPublisher.publishEvent(new ShortUrlClickedEvent(this, code, ip, request.getHeader("Referer"), request.getHeader("User-Agent"), LocalDateTime.now()));
        return longUrl;
    }
}
