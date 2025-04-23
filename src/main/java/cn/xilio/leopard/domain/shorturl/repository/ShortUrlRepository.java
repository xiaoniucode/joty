package cn.xilio.leopard.domain.shorturl.repository;

import cn.xilio.leopard.domain.shorturl.model.ShortUrl;

public interface ShortUrlRepository {
    ShortUrl save(ShortUrl newShortUrl);
}
