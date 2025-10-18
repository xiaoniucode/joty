package cn.xilio.joty.adapter.admin.dto.request;

import cn.xilio.joty.domain.dataobject.User;


public record AddUserRequest(
        /** 用户名 */
        String username,
        /** 昵称 */
        String nickname,
        /** 密码 */
        String password,
        /** 邮箱 */
        String email,
        /** 头像URL */
        String avatar,
        /** 状态 */
        Integer status,
        /** 备注 */
        String remark,
        /** 排序值 */
        Integer sort
) {


    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setEmail(email);
        user.setAvatar(avatar);
        user.setRemark(remark);
        user.setSort(sort);
        return user;
    }
}
