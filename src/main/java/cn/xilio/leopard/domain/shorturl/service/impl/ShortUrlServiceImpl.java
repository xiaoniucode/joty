package cn.xilio.leopard.domain.shorturl.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import cn.xilio.leopard.api.portal.dto.request.CreateBatchShortUrlRequest;
import cn.xilio.leopard.api.portal.dto.request.CreateSingleShortUrlRequest;
import cn.xilio.leopard.api.portal.dto.response.CreateBatchShortUrlResponse;
import cn.xilio.leopard.api.portal.dto.response.CreateSingleShortUrlResponse;
import cn.xilio.leopard.common.page.PageRequest;
import cn.xilio.leopard.common.page.PageResponse;
import cn.xilio.leopard.common.util.WebUtils;
import cn.xilio.leopard.domain.shorturl.event.ShortUrlClickedEvent;
import cn.xilio.leopard.domain.shorturl.event.ShortUrlCreatedEvent;
import cn.xilio.leopard.domain.shorturl.model.ShortUrl;
import cn.xilio.leopard.domain.shorturl.repository.ShortUrlRepository;
import cn.xilio.leopard.domain.shorturl.service.ShortUrlService;
import cn.xilio.leopard.domain.shorturl.service.ext.BloomFilterService;
import cn.xilio.leopard.domain.shorturl.service.ext.ShortCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    @Autowired
    private ShortCodeGenerator shortCodeGenerator;
    @Autowired
    private BloomFilterService bloomFilterService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private ShortUrlRepository shortUrlRepository;
    private final Lock lock = new ReentrantLock();

    /**
     * Create a single short link
     *
     * @param request Create request
     * @return Short link information
     */
    @Override
    public CreateSingleShortUrlResponse createSingle(CreateSingleShortUrlRequest request) {
        lock.lock();
        try {
            // String uid = StpUtil.getLoginIdAsString();
            //检查分组是否存在

            //生成短码
            String code = shortCodeGenerator.genShortCode(request.url());
            //如果生成的短码存在则加随机数重新生成 保证唯一性
            while (bloomFilterService.contain(code)) {
                String salt = RandomUtil.randomString(6);
                code = shortCodeGenerator.genShortCode(request.url(), salt);
            }

            String shortUrl = WebUtils.getDomain() + "/" + code;
            ShortUrl newShortUrl = request.toEntity();

            newShortUrl.setShortCode(code);
            shortUrlRepository.save(newShortUrl);
        } finally {
            lock.unlock();
        }

        //Post short link creation event
        eventPublisher.publishEvent(new ShortUrlCreatedEvent(this));
        return null;
    }

    /**
     * Batch create short links
     *
     * @param request Create request
     * @return Create Results
     */
    @Override
    public CreateBatchShortUrlResponse createBatchShortUrl(CreateBatchShortUrlRequest request) {
        return null;
    }

    /**
     * Batch delete short links
     *
     * @param ids Link ID List
     */
    @Override
    public void deleteShortUrl(List<String> ids) {

    }

    /**
     * Get the pagination list of short links
     *
     * @param request Page request information
     * @return Short chain list
     */
    @Override
    public PageResponse<ShortUrl> getShortUrls(PageRequest request) {
        return null;
    }
}
