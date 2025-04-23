package cn.xilio.leopard.domain.group.repository;

import cn.xilio.leopard.domain.group.model.Group;

import java.util.List;

/**
 * 分组仓储
 */
public interface GroupRepository {

    /**
     * 根据分组ID删除分组
     *
     * @param groupId 分组ID
     * @return 删除是否成功
     */
    boolean deleteById(String groupId);

    /**
     * 根据分组ID更新分组信息
     *
     * @param group 分组实体
     * @return 更新是否成功
     */
    boolean updateById(Group group);

    /**
     * 获取指定用户的所有分组
     *
     * @param userId 用户ID
     * @return 分组列表
     */
    List<Group> getGroupsByUser(String userId);

    /**
     * 根据分组ID查询分组
     *
     * @param groupId 分组ID
     * @return 分组实体，若不存在返回 null
     */
    Group getById(String groupId);

    /**
     * 获取指定用户的分组数量
     *
     * @param userId 用户ID
     * @return 分组数量
     */
    long getCountByUser(String userId);
}
