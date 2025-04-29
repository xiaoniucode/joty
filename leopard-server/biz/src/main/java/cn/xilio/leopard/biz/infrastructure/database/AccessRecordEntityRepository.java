package cn.xilio.leopard.biz.infrastructure.database;

import cn.xilio.leopard.biz.domain.stats.model.AccessRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRecordEntityRepository extends JpaRepository<AccessRecord, String> {

}
