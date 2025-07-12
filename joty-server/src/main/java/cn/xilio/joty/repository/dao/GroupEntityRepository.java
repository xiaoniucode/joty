package cn.xilio.joty.repository.dao;

import cn.xilio.joty.domain.dataobject.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupEntityRepository extends JpaRepository<Group, String>, JpaSpecificationExecutor<Group> {

}
