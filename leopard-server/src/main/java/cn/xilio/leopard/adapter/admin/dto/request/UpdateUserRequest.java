package cn.xilio.leopard.adapter.admin.dto.request;

public record UpdateUserRequest(
        /** 用户ID */
        String id,
        /** 昵称 */
        String nickname,
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

}
