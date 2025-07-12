package cn.xilio.joty.domain.enums;

import lombok.Getter;

/**
 * 用户状态枚举
 */
@Getter
public enum UserStatus {
    /**
     * 正常
     */
    NORMAL(1, "正常"),
    /**
     * 禁用
     */
    DISABLED(0, "禁用"),
    /**
     * 冻结
     */
    FROZEN(-1, "冻结");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 状态描述
     */
    private final String desc;

    UserStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
