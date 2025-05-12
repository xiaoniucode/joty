package cn.xilio.leopard.repository.dao;

import cn.xilio.leopard.domain.dataobject.AccessRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRecordEntityRepository extends JpaRepository<AccessRecord, String> {

}
