package cn.xilio.leopard.repository.dao;

import cn.xilio.leopard.domain.dataobject.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserEntityRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {


}
