package cn.xilio.leopard.domain.shorturl.model;

import lombok.Getter;

/**
 * 短链接状态枚举
 */
@Getter
public enum ShortUrlStatus {
    /**
     * 启用
     */
    ENABLED(1, "启用"),
    /**
     * 禁用
     */
    DISABLED(0, "禁用"),
    /**
     * 过期
     */
    EXPIRED(-1, "过期");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 状态描述
     */
    private final String desc;

    ShortUrlStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
