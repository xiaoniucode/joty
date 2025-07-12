package cn.xilio.joty.repository;



import cn.xilio.joty.domain.dataobject.Group;
import cn.xilio.joty.core.common.page.PageQuery;
import cn.xilio.joty.core.common.page.PageResponse;

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
    long deleteById(String groupId, String userId);

    /**
     * Update group information based on group ID
     *
     * @param group Grouping entity
     * @return Is the update successful
     */
    Group saveGroup(Group group);

    /**
     * Retrieve all groups of the specified user
     *
     * @param query  Page param
     * @param userId USER ID
     * @return Grouped List
     */
    PageResponse<Group> getGroupsByUser(PageQuery query, String userId);

    /**
     * Query grouping based on grouping ID
     *
     * @param groupId Group ID
     * @return Grouping entity, return null if it does not exist
     */
    Group getById(String groupId, String userId);

    /**
     * Get the number of groups for the specified user
     *
     * @param userId USER ID
     * @return Number of groups
     */
    long getCountByUser(String userId);

    void deleteBatch(List<String> ids, String userId);

    void addGroup(Group group);
}
