package cn.xilio.leopard.domain.shorturl.listener;


import cn.xilio.leopard.domain.shorturl.model.ShortUrl;
import cn.xilio.leopard.domain.shorturl.service.ShortUrlService;
import cn.xilio.leopard.domain.shorturl.service.ext.BloomFilterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;


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
        logger.info("Initialize short link code from database to Bloom filter.");
        List<String> codes = getShortUrlCode();
        if (!CollectionUtils.isEmpty(codes)) {
            for (String code : codes) {
                bloomFilterService.put(code);
            }
        }
    }

    /**
     *Get short link code, discard expired ones directly.
     */
    private List<String> getShortUrlCode() {
//        List<ShortUrl> list = shortUrlService.ge();
//        return list.stream().map(ShortUrl::getShortCode).collect(Collectors.toList());
        return null;
    }
}
