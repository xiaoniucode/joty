package cn.xilio.leopard.service.impl;


import cn.dev33.satoken.stp.StpUtil;
import cn.xilio.leopard.adapter.portal.dto.request.CreateGroupRequest;
import cn.xilio.leopard.service.GroupService;
import cn.xilio.leopard.domain.dataobject.Group;
import cn.xilio.leopard.repository.GroupRepository;
import cn.xilio.leopard.core.common.page.PageQuery;
import cn.xilio.leopard.core.common.page.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;

    /**
     * Retrieve Group based on groupId
     *
     * @param groupId ID
     * @return Group info
     */
    @Override
    public Group getById(String groupId) {
        String userId = StpUtil.getLoginIdAsString();
        return groupRepository.getById(groupId, userId);
    }

    /**
     * Save group
     *
     * @param request Create request
     */
    @Override
    public void saveGroup(CreateGroupRequest request) {
        Group group = request.toGroup();
        groupRepository.saveGroup(group);
    }

    /**
     * Delete group
     *
     * @param ids Group ID List
     */
    @Override
    public void deleteGroup(List<String> ids) {
        String userId = StpUtil.getLoginIdAsString();
        groupRepository.deleteBatch(ids, userId);
        //todo Move the short links within the group to the default group
    }

    /**
     * Get the pagination list of groups
     *
     * @param request Page request information
     * @return Group list
     */
    @Override
    public PageResponse<Group> page(PageQuery request) {
        return groupRepository.getGroupsByUser(request, "1");
    }
}
