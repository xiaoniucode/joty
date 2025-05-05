package cn.xilio.leopard.service.impl;


import cn.dev33.satoken.stp.StpUtil;
import cn.xilio.leopard.adapter.portal.dto.request.CreateGroupRequest;
import cn.xilio.leopard.core.common.exception.BizException;
import cn.xilio.leopard.service.GroupService;
import cn.xilio.leopard.domain.dataobject.Group;
import cn.xilio.leopard.repository.GroupRepository;
import cn.xilio.leopard.core.common.page.PageQuery;
import cn.xilio.leopard.core.common.page.PageResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    @Transactional(rollbackOn = Exception.class)
    public void saveGroup(CreateGroupRequest request) {
        String userId = StpUtil.getLoginIdAsString();
        Group group = request.toGroup();
        if (StringUtils.hasText(request.id())){
            Group oldGroup = groupRepository.getById(request.id(), userId);
            BizException.checkNull("1008",oldGroup);
            BeanUtils.copyProperties(oldGroup,group);
        }
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
