package cn.xilio.leopard.domain.group.service.impl;

import cn.xilio.leopard.domain.group.model.Group;
import cn.xilio.leopard.domain.group.repository.GroupRepository;
import cn.xilio.leopard.domain.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;
    /**
     * 根据groupId获取Group
     *
     * @param groupId ID
     * @return 分组
     */
    @Override
    public Group getById(String groupId) {
        return groupRepository.getById(groupId);
    }
}
