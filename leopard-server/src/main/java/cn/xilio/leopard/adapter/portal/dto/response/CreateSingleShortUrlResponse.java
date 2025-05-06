package cn.xilio.leopard.adapter.portal.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record CreateSingleShortUrlResponse(
        String title,
        String shortUrl,
        String originalUrl,
        String qrUrl,
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
        LocalDate expiredAt

) {
}
