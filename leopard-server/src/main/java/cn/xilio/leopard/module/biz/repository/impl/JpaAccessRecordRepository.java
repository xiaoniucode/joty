package cn.xilio.leopard.module.biz.repository.impl;



import cn.xilio.leopard.module.biz.repository.AccessRecordRepository;
import cn.xilio.leopard.module.biz.repository.dao.AccessRecordEntityRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class JpaAccessRecordRepository implements AccessRecordRepository {
    @Resource
    private AccessRecordEntityRepository accessRecordEntityRepository;


}

