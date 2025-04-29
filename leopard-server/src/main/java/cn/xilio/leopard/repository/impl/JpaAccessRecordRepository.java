package cn.xilio.leopard.repository.impl;



import cn.xilio.leopard.repository.AccessRecordRepository;
import cn.xilio.leopard.repository.dao.AccessRecordEntityRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class JpaAccessRecordRepository implements AccessRecordRepository {
    @Resource
    private AccessRecordEntityRepository accessRecordEntityRepository;


}

