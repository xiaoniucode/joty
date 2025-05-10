package cn.xilio.leopard.adapter.admin.dto.request;

public record UpdateUserRequest(
        String id,
        String nickname,
        String email,
        String avatar,
        Integer status,
        String remark,
        Integer sort
) {

}
