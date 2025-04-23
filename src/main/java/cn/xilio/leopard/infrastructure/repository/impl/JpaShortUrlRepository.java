package cn.xilio.leopard.infrastructure.repository.impl;


import cn.xilio.leopard.domain.shorturl.model.ShortUrl;
import cn.xilio.leopard.domain.shorturl.repository.ShortUrlRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class JpaShortUrlRepository implements ShortUrlRepository {
    private final ShortUrlEntityRepository shortUrlEntityRepository;


    @Override
    public ShortUrl save(ShortUrl newShortUrl) {
        return shortUrlEntityRepository.save(newShortUrl);
    }
}

@Repository
interface ShortUrlEntityRepository extends JpaRepository<ShortUrl, String> {
}

