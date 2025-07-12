package cn.xilio.joty.repository.dao;

import cn.xilio.joty.domain.dataobject.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  UserEntityRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    @Modifying
    @Query("UPDATE User u SET u.deleted = 1 WHERE u.id IN :ids")
    void updateDeletedStatusByIds(@Param("ids") List<String> ids );
    @Query("SELECT u.apiKey FROM User u WHERE u.id = :userId")
    String findApiKeyById(@Param("userId") String userId);
}
