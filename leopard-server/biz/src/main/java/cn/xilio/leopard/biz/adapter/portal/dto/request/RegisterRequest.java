package cn.xilio.leopard.biz.adapter.portal.dto.request;


import cn.xilio.leopard.biz.domain.user.model.User;

public record RegisterRequest (
        String username,
        String password,
        String avatar
){
    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAvatar(avatar);
        return user;
    }
}
