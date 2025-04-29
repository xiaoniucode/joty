package cn.xilio.leopard.repository.dao;

import cn.xilio.leopard.domain.dataobject.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface  ShortUrlEntityRepository extends JpaRepository<ShortUrl, String>, JpaSpecificationExecutor<ShortUrl> {

}
