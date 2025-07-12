package cn.xilio.joty.adapter.portal.dto.request;


import cn.xilio.joty.domain.dataobject.User;

public record RegisterRequest (
        /** 用户名 */
        String username,
        /** 密码 */
        String password,
        /** 头像URL */
        String avatar,
        /** 邮箱 */
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
