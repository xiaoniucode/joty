package cn.xilio.leopard.module.biz.adapter.portal.dto.request;


import cn.xilio.leopard.module.biz.domain.dataobject.Group;
import jakarta.validation.constraints.NotEmpty;

public record CreateGroupRequest(
        String id,
        @NotEmpty
        String name,
        String remark
) {
    public Group toGroup() {
        Group group = new Group();
        group.setId(id);
        group.setName(name);
        group.setRemark(remark);
        return group;
    }
}
