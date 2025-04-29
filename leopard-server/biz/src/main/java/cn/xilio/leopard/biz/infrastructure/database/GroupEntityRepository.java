package cn.xilio.leopard.biz.infrastructure.database;

import cn.xilio.leopard.biz.domain.group.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupEntityRepository extends JpaRepository<Group, String>, JpaSpecificationExecutor<Group> {

}
