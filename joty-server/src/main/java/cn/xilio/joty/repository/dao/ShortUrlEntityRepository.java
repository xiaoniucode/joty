package cn.xilio.joty.repository.dao;

import cn.xilio.joty.domain.dataobject.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  ShortUrlEntityRepository extends JpaRepository<ShortUrl, String>, JpaSpecificationExecutor<ShortUrl> {
    @Modifying
    @Query("UPDATE ShortUrl su SET su.groupId = :newGroup WHERE  su.groupId!=:newGroup AND su.groupId IN :groupIds")
    void updateGroupId(@Param("groupIds") List<String> groupIds, @Param("newGroup") String newGroup);
}
