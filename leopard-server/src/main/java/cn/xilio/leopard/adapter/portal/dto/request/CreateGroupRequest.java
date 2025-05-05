package cn.xilio.leopard.adapter.portal.dto.request;


import cn.xilio.leopard.domain.dataobject.Group;
import jakarta.validation.constraints.NotEmpty;

public record CreateGroupRequest(
        String id,
        @NotEmpty
        String name,
        String remark,
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
