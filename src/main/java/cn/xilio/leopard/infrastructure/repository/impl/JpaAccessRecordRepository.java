package cn.xilio.leopard.infrastructure.repository.impl;

import cn.xilio.leopard.domain.stats.model.AccessRecord;
import cn.xilio.leopard.domain.stats.repository.AccessRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@AllArgsConstructor
@Repository
public class JpaAccessRecordRepository implements AccessRecordRepository {

    private final AccessRecordEntityRepository accessRecordEntityRepository;




}
@Repository
interface AccessRecordEntityRepository extends JpaRepository<AccessRecord, String> {

}
