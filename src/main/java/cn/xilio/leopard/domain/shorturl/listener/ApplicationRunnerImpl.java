package cn.xilio.leopard.domain.shorturl.listener;


import cn.xilio.leopard.common.exception.BizException;
import cn.xilio.leopard.common.page.PageQuery;
import cn.xilio.leopard.common.page.PageResponse;
import cn.xilio.leopard.domain.shorturl.model.ShortUrl;
import cn.xilio.leopard.domain.shorturl.service.ShortUrlService;
import cn.xilio.leopard.domain.shorturl.service.ext.BloomFilterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;


@Component
public class ApplicationRunnerImpl implements ApplicationRunner {
    private final Logger logger = LoggerFactory.getLogger(ApplicationRunnerImpl.class);
    @Autowired
    private ShortUrlService shortUrlService;
    @Autowired
    private BloomFilterService bloomFilterService;

    @Override
    public void run(ApplicationArguments args) {
        initShortUrlCode();
    }

    private void initShortUrlCode() {
        logger.info("Starting to initialize short link codes from database to Bloom filter.");
        int page = 1;
        int pageSize = 2000;
        PageResponse<ShortUrl> pageResponse;

        try {
            do {
                PageQuery pageRequest = PageQuery.of(page, pageSize);
                pageResponse = shortUrlService.getShortUrls(pageRequest);
                logger.debug("Processing page {} with {} records.", page, pageResponse.getRecords().size());

                for (ShortUrl shortUrl : pageResponse.getRecords()) {
                    LocalDateTime expiredAt = shortUrl.getExpiredAt();
                    String shortCode = shortUrl.getShortCode();
                    if (!StringUtils.hasText(shortCode) || ObjectUtils.isEmpty(expiredAt) || expiredAt.isBefore(LocalDateTime.now())) {
                        continue;
                    }
                    bloomFilterService.put(shortCode);
                }
                // Increasing page numbers
                page++;
            } while (pageResponse.getHasMore());
            logger.info("Finished initializing {} short link codes to Bloom filter.", page * pageSize);
        } catch (Exception e) {
            logger.error("Failed to initialize short link codes to Bloom filter.", e);
            throw new BizException("1005");
        }
    }
}
