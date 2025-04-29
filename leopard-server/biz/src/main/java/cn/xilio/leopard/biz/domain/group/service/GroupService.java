package cn.xilio.leopard.biz.domain.group.service;


import cn.xilio.leopard.biz.adapter.portal.dto.request.CreateGroupRequest;
import cn.xilio.leopard.biz.domain.group.model.Group;
import cn.xilio.leopard.core.common.page.PageQuery;
import cn.xilio.leopard.core.common.page.PageResponse;

import java.util.List;

public interface GroupService {
    /**
     * Retrieve Group based on groupId
     *
     * @param groupId ID
     * @return Group info
     */
    public Group getById(String groupId);

    /**
     * Save group
     *
     * @param request Create request
     */
    void saveGroup(CreateGroupRequest request);

    /**
     * Delete group
     *
     * @param ids Group ID List
     */
    void deleteGroup(List<String> ids);

    /**
     * Get the pagination list of groups
     *
     * @param request Page request information
     * @return Group list
     */
    PageResponse<Group> page(PageQuery request);
}
