package cn.xilio.leopard.adapter.admin.dto.request;

import cn.xilio.leopard.domain.dataobject.User;

public record UpdateUserRequest(
        String id,
        String nickname,
        String email,
        String avatar,
        Integer status,
        String remark
) {
    public User toUser() {
        User user = new User();
        return user;
    }
}
