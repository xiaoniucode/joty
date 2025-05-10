package cn.xilio.leopard.adapter.portal.dto.request;


import cn.xilio.leopard.domain.dataobject.User;

public record RegisterRequest (
        String username,
        String password,
        String avatar,
        String email
){
    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAvatar(avatar);
        user.setEmail(email);
        return user;
    }
}
