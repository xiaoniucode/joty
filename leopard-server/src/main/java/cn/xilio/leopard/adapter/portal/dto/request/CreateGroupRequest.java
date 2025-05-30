package cn.xilio.leopard.adapter.portal.dto.request;


import cn.xilio.leopard.domain.dataobject.Group;
import jakarta.validation.constraints.NotEmpty;

public record CreateGroupRequest(
        /** 分组ID */
        String id,
        /** 分组名称 */
        @NotEmpty
        String name,
        /** 分组备注 */
        String remark,
        /** 排序值 */
        Integer sort
) {
    public Group toGroup() {
        Group group = new Group();
        group.setId(id);
        group.setName(name);
        group.setRemark(remark);
        group.setSort(sort);
        return group;
    }
}
