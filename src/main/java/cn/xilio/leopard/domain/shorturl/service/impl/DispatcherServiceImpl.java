package cn.xilio.leopard.domain.shorturl.service.impl;

import cn.xilio.leopard.common.exception.BizException;
import cn.xilio.leopard.domain.shorturl.service.DispatcherService;
import cn.xilio.leopard.domain.shorturl.service.ext.BloomFilterService;
import cn.xilio.leopard.infrastructure.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class DispatcherServiceImpl implements DispatcherService {
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private BloomFilterService bloomFilterService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    /**
     * Obtain long links
     *
     * @param code short code
     * @return Long link
     */
    @Override
    public String getLongUrl(String code) {
        BizException.checkExpr("1001", !bloomFilterService.contain(code));
        return "";
    }
}
