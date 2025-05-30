package cn.xilio.leopard.adapter.portal.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record SingleShortUrlResponse(
        /** 标题 */
        String title,
        /** 短链接 */
        String shortUrl,
        /** 原始URL */
        String originalUrl,
        /** 二维码URL */
        String qrUrl,
        /** 过期时间 */
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
        LocalDate expiredAt
) {
}
