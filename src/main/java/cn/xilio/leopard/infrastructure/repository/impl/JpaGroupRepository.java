package cn.xilio.leopard.infrastructure.repository.impl;

import cn.xilio.leopard.domain.group.model.Group;
import cn.xilio.leopard.domain.group.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class JpaGroupRepository implements GroupRepository {

    private final GroupEntityRepository groupEntityRepository;


}
@Repository
interface GroupEntityRepository extends JpaRepository<Group, String> {
}
