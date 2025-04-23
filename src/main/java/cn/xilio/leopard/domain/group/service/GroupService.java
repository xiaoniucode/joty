package cn.xilio.leopard.domain.group.service;

import cn.xilio.leopard.domain.group.model.Group;

public interface GroupService {
    /**
     * 根据groupId获取Group
     * @param groupId ID
     * @return 分组
     */
    public Group getById(String groupId);
}
