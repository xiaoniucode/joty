package cn.xilio.leopard.domain.group.repository;

import cn.xilio.leopard.domain.group.model.Group;

import java.util.List;

/**
 * Group storage
 */
public interface GroupRepository {

    /**
     * Delete a group based on its ID
     *
     * @param groupId Group ID
     * @return Was the deletion successful
     */
    boolean deleteById(String groupId);

    /**
     * Update group information based on group ID
     *
     * @param group Grouping entity
     * @return Is the update successful
     */
    boolean updateById(Group group);

    /**
     * Retrieve all groups of the specified user
     *
     * @param userId USER ID
     * @return Grouped List
     */
    List<Group> getGroupsByUser(String userId);

    /**
     * Query grouping based on grouping ID
     *
     * @param groupId Group ID
     * @return Grouping entity, return null if it does not exist
     */
    Group getById(String groupId);

    /**
     * Get the number of groups for the specified user
     *
     * @param userId USER ID
     * @return Number of groups
     */
    long getCountByUser(String userId);
}
