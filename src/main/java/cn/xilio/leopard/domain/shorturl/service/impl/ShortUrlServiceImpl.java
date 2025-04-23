package cn.xilio.leopard.domain.shorturl.service.impl;

import cn.xilio.leopard.api.portal.dto.request.CreateBatchShortUrlRequest;
import cn.xilio.leopard.api.portal.dto.request.CreateSingleShortUrlRequest;
import cn.xilio.leopard.api.portal.dto.response.CreateBatchShortUrlResponse;
import cn.xilio.leopard.api.portal.dto.response.CreateSingleShortUrlResponse;
import cn.xilio.leopard.common.page.PageRequest;
import cn.xilio.leopard.common.page.PageResponse;
import cn.xilio.leopard.domain.shorturl.event.ShortUrlClickedEvent;
import cn.xilio.leopard.domain.shorturl.event.ShortUrlCreatedEvent;
import cn.xilio.leopard.domain.shorturl.model.ShortUrl;
import cn.xilio.leopard.domain.shorturl.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    private Lock lock = new ReentrantLock();

    //todo test
    public String getLongUrl(String shortCode, String ip) {
        eventPublisher.publishEvent(new ShortUrlClickedEvent(this, shortCode, ip, LocalDateTime.now()));
        return "a";
    }


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

        } catch (Exception e) {

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
