package cn.xilio.leopard.domain.group.service.impl;

import cn.xilio.leopard.api.portal.dto.request.CreateGroupRequest;
import cn.xilio.leopard.common.page.PageQuery;
import cn.xilio.leopard.common.page.PageResponse;
import cn.xilio.leopard.domain.group.model.Group;
import cn.xilio.leopard.domain.group.repository.GroupRepository;
import cn.xilio.leopard.domain.group.service.GroupService;
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
        String userId = "1";
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
        group.setCreatedBy("1");
        groupRepository.saveGroup(group);
    }

    /**
     * Delete group
     *
     * @param ids Group ID List
     */
    @Override
    public void deleteGroup(List<String> ids) {
        String userId = "1";
        groupRepository.deleteBatch(ids, userId);
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
