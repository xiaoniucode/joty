package cn.xilio.leopard.domain.shorturl.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.xilio.leopard.api.portal.dto.request.CreateBatchShortUrlRequest;
import cn.xilio.leopard.api.portal.dto.request.CreateSingleShortUrlRequest;
import cn.xilio.leopard.api.portal.dto.response.CreateBatchShortUrlResponse;
import cn.xilio.leopard.api.portal.dto.response.CreateSingleShortUrlResponse;
import cn.xilio.leopard.common.exception.BizException;
import cn.xilio.leopard.common.page.PageQuery;
import cn.xilio.leopard.common.page.PageResponse;
import cn.xilio.leopard.common.util.WebUtils;
import cn.xilio.leopard.domain.file.service.UploadService;
import cn.xilio.leopard.domain.group.model.Group;
import cn.xilio.leopard.domain.group.service.GroupService;
import cn.xilio.leopard.domain.shorturl.event.ShortUrlCreatedEvent;
import cn.xilio.leopard.domain.shorturl.model.ShortUrl;
import cn.xilio.leopard.domain.shorturl.repository.ShortUrlRepository;
import cn.xilio.leopard.domain.shorturl.service.ShortUrlService;
import cn.xilio.leopard.domain.shorturl.service.ext.BloomFilterService;
import cn.xilio.leopard.domain.shorturl.service.ext.QRGenerator;
import cn.xilio.leopard.domain.shorturl.service.ext.ShortCodeGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Autowired
    private GroupService groupService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UploadService uploadService;
    private final Lock lock = new ReentrantLock();

    /**
     * Create a single short link
     *
     * @param request Create request
     * @return Short link information
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public CreateSingleShortUrlResponse createSingle(CreateSingleShortUrlRequest request) {
        lock.lock();
        try {
            Group group = groupService.getById(request.groupId());
            //检查分组是否存在
            BizException.checkNull("6005", group);
            //生成短码
            String code = shortCodeGenerator.genShortCode(request.originalUrl());
            //如果生成的短码存在则加随机数重新生成 保证唯一性
            while (bloomFilterService.contain(code)) {
                String salt = RandomUtil.randomString(6);
                code = shortCodeGenerator.genShortCode(request.originalUrl(), salt);
            }
            String shortUrl = WebUtils.getDomain() + "/" + code;
            InputStream inputStream = QRGenerator.generateQRCode(shortUrl);
            String qrCodeUrl = uploadService.upload(inputStream);

            ShortUrl newShortUrl = request.toEntity();
            newShortUrl.setDomain(WebUtils.getDomain());
            newShortUrl.setShortCode(code);
            newShortUrl.setQrUrl(qrCodeUrl);
            newShortUrl.setShortUrl(shortUrl);
            ShortUrl saveResult = shortUrlRepository.save(newShortUrl);
            bloomFilterService.put(code);

            //Post short link creation event
            eventPublisher.publishEvent(new ShortUrlCreatedEvent(this));
            return new CreateSingleShortUrlResponse(shortUrl, request.originalUrl(), qrCodeUrl);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Batch create short links
     *
     * @param request Create request
     * @return Create Results
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public CreateBatchShortUrlResponse createBatchShortUrl(CreateBatchShortUrlRequest request) {
        List<CreateSingleShortUrlResponse> list = new ArrayList<>();
        List<String> urls = request.urls();
        for (String originalUrl : urls) {
            CreateSingleShortUrlRequest single = new CreateSingleShortUrlRequest(
                    "title",
                    originalUrl,
                    "1",
                    LocalDateTime.now(),
                    "Batch Create");
            CreateSingleShortUrlResponse created = createSingle(single);
            list.add(created);
        }
        return new CreateBatchShortUrlResponse(list);
    }

    /**
     * Batch delete short links
     *
     * @param ids Link ID List
     */
    @Override
    public void deleteShortUrl(List<String> ids) {
        String userId = "1";
        shortUrlRepository.deleteByIds(ids, userId);
    }

    /**
     * Get the pagination list of short links
     *
     * @param request Page request information
     * @return Short chain list
     */
    @Override
    public PageResponse<ShortUrl> getShortUrls(PageQuery request) {
        return shortUrlRepository.page(request, "1");
    }

    /**
     * Get short link information
     *
     * @param id ID
     * @return Short link information
     */
    @Override
    public ShortUrl getShortUrlInfo(String id) {
        String userId = "1";
        return shortUrlRepository.findById(id, userId);
    }

    /**
     * Get short link information
     *
     * @param code Short Code
     * @return Short link information
     */
    @Override
    public ShortUrl getByShortCode(String code) {
        return shortUrlRepository.findByShortCode(code);
    }
}
