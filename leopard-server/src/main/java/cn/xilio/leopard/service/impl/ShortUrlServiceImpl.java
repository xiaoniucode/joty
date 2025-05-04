package cn.xilio.leopard.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;

import cn.xilio.leopard.adapter.portal.dto.request.CreateBatchShortUrlRequest;
import cn.xilio.leopard.adapter.portal.dto.request.CreateSingleShortUrlRequest;
import cn.xilio.leopard.adapter.portal.dto.request.UpdateShortUrlRequest;
import cn.xilio.leopard.adapter.portal.dto.response.CreateBatchShortUrlResponse;
import cn.xilio.leopard.adapter.portal.dto.response.CreateSingleShortUrlResponse;
import cn.xilio.leopard.service.ShortUrlService;
import cn.xilio.leopard.service.UploadService;
import cn.xilio.leopard.domain.dataobject.Group;
import cn.xilio.leopard.service.GroupService;
import cn.xilio.leopard.domain.event.ShortUrlCreatedEvent;
import cn.xilio.leopard.domain.dataobject.ShortUrl;
import cn.xilio.leopard.repository.ShortUrlRepository;

import cn.xilio.leopard.domain.service.BloomFilterService;
import cn.xilio.leopard.domain.service.QRGenerator;
import cn.xilio.leopard.domain.service.ShortCodeGenerator;
import cn.xilio.leopard.core.common.exception.BizException;
import cn.xilio.leopard.core.common.page.PageQuery;
import cn.xilio.leopard.core.common.page.PageResponse;
import cn.xilio.leopard.core.common.util.WebUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDate;
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
    private UploadService uploadService;
    private final Lock lock = new ReentrantLock();
    private final static String DEFAULT_GROUP_ID = "1";

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
            String qrCodeUrl = uploadService.upload(inputStream, "png");

            ShortUrl newShortUrl = request.toEntity();
            newShortUrl.setDomain(WebUtils.getDomain());
            newShortUrl.setShortCode(code);
            newShortUrl.setQrUrl(qrCodeUrl);
            newShortUrl.setShortUrl(shortUrl);
            ShortUrl saveResult = shortUrlRepository.save(newShortUrl);
            bloomFilterService.put(code);

            //Post short link creation event
            eventPublisher.publishEvent(new ShortUrlCreatedEvent(this, code, request.originalUrl()));
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
                    "未命名",
                    originalUrl,
                    DEFAULT_GROUP_ID,
                    null,
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
    @Transactional(rollbackOn = Exception.class)
    public void deleteShortUrl(List<String> ids) {
        String userId = StpUtil.getLoginIdAsString();
        shortUrlRepository.deleteByIds(ids, userId);
    }

    /**
     * Get the pagination list of short links
     *
     * @param request Page request information
     * @return Short chain list
     */
    @Override
    public PageResponse<ShortUrl> getShortUrlsByUser(PageQuery request) {
        String userId = StpUtil.getLoginIdAsString();
        return shortUrlRepository.page(request, userId);
    }

    /**
     * Get the pagination list of short links
     *
     * @param request Page request information
     * @return Short chain list
     */
    @Override
    public PageResponse<ShortUrl> getShortUrls(PageQuery request) {
        return shortUrlRepository.page(request);
    }

    /**
     * Get short link information
     *
     * @param id ID
     * @return Short link information
     */
    @Override
    public ShortUrl getShortUrlInfo(String id) {
        String userId = StpUtil.getLoginIdAsString();
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

    /**
     * Delete expired short links
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public long deleteExpiredUrls() {
        return shortUrlRepository.deleteExpiredUrls();
    }

    /**
     * Update short link
     *
     * @param request Update info
     */
    @Override
    public void update(UpdateShortUrlRequest request) {
        ShortUrl old = shortUrlRepository.findById(request.id(), StpUtil.getLoginIdAsString());
        BizException.checkNull("1007", old);
        BeanUtils.copyProperties(request, old);
        shortUrlRepository.save(old);
    }
}
