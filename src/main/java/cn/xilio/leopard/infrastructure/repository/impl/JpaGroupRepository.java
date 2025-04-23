package cn.xilio.leopard.infrastructure.repository.impl;

import cn.xilio.leopard.domain.group.model.Group;
import cn.xilio.leopard.domain.group.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class JpaGroupRepository implements GroupRepository {

    private final GroupEntityRepository groupEntityRepository;


    /**
     * Delete a group based on its ID
     *
     * @param groupId Group ID
     * @return Was the deletion successful
     */
    @Override
    public boolean deleteById(String groupId) {
        return false;
    }

    /**
     * Update group information based on group ID
     *
     * @param group Grouping entity
     * @return Is the update successful
     */
    @Override
    public boolean updateById(Group group) {
        return false;
    }

    /**
     * Retrieve all groups of the specified user
     *
     * @param userId USER ID
     * @return Grouped List
     */
    @Override
    public List<Group> getGroupsByUser(String userId) {
        return List.of();
    }

    /**
     * Query grouping based on grouping ID
     *
     * @param groupId Group ID
     * @return Grouping entity, return null if it does not exist
     */
    @Override
    public Group getById(String groupId) {
        return groupEntityRepository.findById(groupId).orElse(null);
    }

    /**
     * Get the number of groups for the specified user
     *
     * @param userId USER ID
     * @return Number of groups
     */
    @Override
    public long getCountByUser(String userId) {
        return 0;
    }
}
@Repository
interface GroupEntityRepository extends JpaRepository<Group, String> {
}
