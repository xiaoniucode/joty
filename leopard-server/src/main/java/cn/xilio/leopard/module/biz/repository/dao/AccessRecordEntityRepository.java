package cn.xilio.leopard.module.biz.repository.dao;

import cn.xilio.leopard.module.biz.domain.dataobject.AccessRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRecordEntityRepository extends JpaRepository<AccessRecord, String> {

}
