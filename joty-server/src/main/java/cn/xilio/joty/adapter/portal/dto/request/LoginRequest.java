package cn.xilio.joty.adapter.portal.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        /** 用户名 */
        @NotEmpty
        String username,
        /** 密码 */
        @NotEmpty
        String password
) {
}
