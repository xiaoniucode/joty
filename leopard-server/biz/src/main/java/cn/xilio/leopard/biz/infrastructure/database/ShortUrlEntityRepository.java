package cn.xilio.leopard.biz.infrastructure.database;

import cn.xilio.leopard.biz.domain.shorturl.model.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface  ShortUrlEntityRepository extends JpaRepository<ShortUrl, String>, JpaSpecificationExecutor<ShortUrl> {

}
