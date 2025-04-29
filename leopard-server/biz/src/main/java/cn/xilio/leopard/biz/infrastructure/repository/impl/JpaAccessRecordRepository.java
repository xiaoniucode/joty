package cn.xilio.leopard.biz.infrastructure.repository.impl;


import cn.xilio.leopard.biz.domain.stats.repository.AccessRecordRepository;
;
import cn.xilio.leopard.biz.infrastructure.database.AccessRecordEntityRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class JpaAccessRecordRepository implements AccessRecordRepository {
    @Resource
    private AccessRecordEntityRepository accessRecordEntityRepository;


}

