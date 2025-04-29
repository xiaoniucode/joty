package cn.xilio.leopard.biz.infrastructure.database;

import cn.xilio.leopard.biz.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserEntityRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {


}
