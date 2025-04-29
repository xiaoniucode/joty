package cn.xilio.leopard.module.biz.repository.dao;

import cn.xilio.leopard.module.biz.domain.dataobject.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupEntityRepository extends JpaRepository<Group, String>, JpaSpecificationExecutor<Group> {

}
