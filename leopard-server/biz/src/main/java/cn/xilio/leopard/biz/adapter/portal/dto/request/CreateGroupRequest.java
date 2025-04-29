package cn.xilio.leopard.biz.adapter.portal.dto.request;


import cn.xilio.leopard.biz.domain.group.model.Group;
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
